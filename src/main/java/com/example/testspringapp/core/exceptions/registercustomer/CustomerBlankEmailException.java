package com.example.testspringapp.core.exceptions.registercustomer;

public class CustomerBlankEmailException extends RuntimeException{
    public CustomerBlankEmailException(String message){
        super(message);
    }
}
