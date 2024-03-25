package com.microservice.order.service;

import brave.Span;
import brave.Tracer;
import com.microservice.order.DAO.OrderDAO;
import com.microservice.order.DTO.InventoryResponse;
import com.microservice.order.DTO.OrderLineItemsDTO;
import com.microservice.order.DTO.OrderRequest;
import com.microservice.order.event.OrderPlaceEvent;
import com.microservice.order.model.Order;
import com.microservice.order.model.OrderLineItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private KafkaTemplate<String, OrderPlaceEvent> kafkaTemplate;
    @Autowired
    private Tracer tracer;
    public void placeOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .purchaseNumber(UUID.randomUUID().toString())
                .build();
        List<OrderLineItems> orderLineItems = orderRequest
                .getOrderLineItemsDTOs()
                .stream()
                .map(this::mapToDTO)
                .toList();
        order.setItems(orderLineItems);
        List<String> codesSku = order.getItems()
                .stream()
                .map(OrderLineItems::getCodeSku)
                .collect(Collectors.toList());
        Span inventoryServiceLookup = tracer.nextSpan().name("InventoryServiceLookup");

        try(Tracer.SpanInScope isLookup = tracer.withSpanInScope(inventoryServiceLookup.start())) {
            inventoryServiceLookup.tag("call", "inventory-service");

            InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                    .uri("http://inventory-service/api/inventory/",
                            uriBuilder -> uriBuilder.queryParam("codigoSku", codesSku).build())
                    .retrieve().bodyToMono(InventoryResponse[].class)
                    .block();
            boolean allProductsInStock = Arrays.stream(inventoryResponses)
                    .allMatch(InventoryResponse::isInStock);
            if(allProductsInStock) {
                orderDAO.save(order);
                kafkaTemplate.send("notificationTopic", new OrderPlaceEvent(order.getPurchaseNumber()));
            } else  {
                throw new IllegalArgumentException("Thre product is not in stock");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            inventoryServiceLookup.flush();
        }
    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO itemDTO) {
        return OrderLineItems.builder()
                .codeSku(itemDTO.getCodeSku())
                .quantity(itemDTO.getQuantity())
                .price(itemDTO.getPrice())
                .build();
    }
}
