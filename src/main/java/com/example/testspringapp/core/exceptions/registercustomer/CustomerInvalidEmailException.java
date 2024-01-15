package com.example.testspringapp.core.exceptions.registercustomer;

public class CustomerInvalidEmailException extends RuntimeException {
    public CustomerInvalidEmailException(String message) {
        super(message);
    }
}
