package com.example.beautysalon.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(Long id) {
        super("Could not find customer with ID " + id);
    }
    public CustomerNotFoundException(String username) {
        super("Could not find customer with username " + username);
    }
}
