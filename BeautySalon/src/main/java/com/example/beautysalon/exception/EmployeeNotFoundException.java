package com.example.beautysalon.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id) {
        super("Could not find stylist with id " + id);
    }
}
