package com.example.testspringapp.core.exceptions.registerproducttocustomer;

public class CustomerNotChosenException extends RuntimeException{
    public CustomerNotChosenException(String message) {
        super(message);
    }
}
