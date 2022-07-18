package com.example.beautysalon.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(Long id) {
        super("Could not find account with id " + id);
    }
}
