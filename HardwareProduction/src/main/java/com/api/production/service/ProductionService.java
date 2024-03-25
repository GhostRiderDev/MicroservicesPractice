package com.api.production.service;

import com.api.production.wrapper.ProductionWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductionService {
    ResponseEntity<String> addProduction(Map<String, Object> reqMap);
    ResponseEntity<ProductionWrapper> getProduction(Integer id);
    ResponseEntity<List<ProductionWrapper>> getProductions();
    ResponseEntity<String> updateProduction(Integer id, Map<String, Object> reqMap);
    ResponseEntity<String> deleteProduction(Integer id);
}
