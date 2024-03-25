package com.microservice.user.exception.controller;

import com.microservice.user.exception.UserNotFoundException;
import com.microservice.user.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse> handleUserNotFoundException(UserNotFoundException userNotFoundException) {
        String message = userNotFoundException.getMessage();
        ApiResponse apiResponse = ApiResponse.builder()
                .message(message)
                .success(true)
                .status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }
}
