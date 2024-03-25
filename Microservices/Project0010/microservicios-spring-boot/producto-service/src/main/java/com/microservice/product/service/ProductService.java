package com.microservice.product.service;

import com.microservice.product.DTO.ProductRequest;
import com.microservice.product.DTO.ProductResponse;

import java.util.List;

public interface ProductService {
    public void createProduct(ProductRequest productRequest);
    public List<ProductResponse> getProducts();
}
