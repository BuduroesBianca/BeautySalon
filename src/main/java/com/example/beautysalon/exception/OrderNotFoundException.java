package com.example.beautysalon.exception;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(Long id) {
        super("Could not find order with ID " + id);
    }
}
