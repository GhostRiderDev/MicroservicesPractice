package com.microservice.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderLineItemsDTO {
    private Integer id;
    private String codeSku;
    private BigDecimal price;
    private Integer quantity;
}
