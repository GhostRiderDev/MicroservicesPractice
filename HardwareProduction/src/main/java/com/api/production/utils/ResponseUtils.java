package com.api.production.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {
    public static ResponseEntity<String> getResponseEntity(String message, HttpStatus httpStatus) {
        return new ResponseEntity<>("{\"message\": \"" + message + "\"}", httpStatus);
    }
}
