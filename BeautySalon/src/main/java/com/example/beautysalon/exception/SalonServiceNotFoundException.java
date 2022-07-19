package com.example.beautysalon.exception;

public class SalonServiceNotFoundException extends RuntimeException {

    public SalonServiceNotFoundException(Long id) {
        super("Could not find salon service with id " + id);
    }
    public SalonServiceNotFoundException(String name) {
        super("Could not find salon service with name " + name);
    }
}
