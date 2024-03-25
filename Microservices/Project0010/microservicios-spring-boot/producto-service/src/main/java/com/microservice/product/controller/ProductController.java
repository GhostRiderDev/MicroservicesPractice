package com.microservice.product.controller;

import com.microservice.product.DTO.ProductRequest;
import com.microservice.product.DTO.ProductResponse;
import com.microservice.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProducts() {
        return productService.getProducts();
    }
}
