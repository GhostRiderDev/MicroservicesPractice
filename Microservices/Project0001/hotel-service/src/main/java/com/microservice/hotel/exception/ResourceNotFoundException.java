package com.microservice.hotel.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException() {
        super("Hotel not found");
    }
}
