package com.example.testspringapp.core.exceptions.registerproduct;

public class ProductInvalidAmortizationValueException extends RuntimeException {
    public ProductInvalidAmortizationValueException(String message) {
        super(message);
    }
}
