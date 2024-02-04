package com.example.testspringapp.core.exceptions.registerproducttocustomer;

public class ProductNotChosenException extends RuntimeException{
    public ProductNotChosenException(String message) {
        super(message);
    }
}
