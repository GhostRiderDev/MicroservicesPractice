package com.microservices.calification.exception.handler;

import com.microservices.calification.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class HandlerResourceNotFoundException {
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException exception) {
        Map<String, Object> mapResponse = new HashMap<>();
        mapResponse.put("message", exception.getMessage());
        mapResponse.put("success", false);
        mapResponse.put("status", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mapResponse);
    }

}
