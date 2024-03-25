package com.microservice.product.DAO;

import com.microservice.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends MongoRepository<Product, String> {
}
