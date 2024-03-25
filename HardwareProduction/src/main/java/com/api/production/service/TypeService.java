package com.api.production.service;

import com.api.production.wrapper.TypeWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface TypeService {
    ResponseEntity<String> saveType(Map<String, String> reqMap);
    ResponseEntity<TypeWrapper> getType(Integer id);
    ResponseEntity<List<TypeWrapper>> getAllTypes();
    ResponseEntity<String> updateType(Integer id, Map<String, String> reqMap);
    ResponseEntity<String> deleteTypeById(Integer id);
}
