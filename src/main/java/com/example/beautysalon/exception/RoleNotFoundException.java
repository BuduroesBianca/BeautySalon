package com.example.beautysalon.exception;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(Long id) {
        super("Could not find role with ID " + id);
    }
}
