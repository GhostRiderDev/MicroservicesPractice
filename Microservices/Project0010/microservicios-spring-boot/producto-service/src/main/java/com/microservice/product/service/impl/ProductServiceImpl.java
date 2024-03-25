package com.microservice.product.service.impl;

import com.microservice.product.DAO.ProductDAO;
import com.microservice.product.DTO.ProductRequest;
import com.microservice.product.DTO.ProductResponse;
import com.microservice.product.model.Product;
import com.microservice.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;
    @Override
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .description(productRequest.getDescription())
                .build();
        productDAO.save(product);
        log.info("Product {} was save with success", product.getId());
    }

    @Override
    public List<ProductResponse> getProducts() {
        List<Product> products = productDAO.findAll();
        return  products.stream()
                        .map(this::mapToProductResponse)
                        .collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice()).build();
    }

}
