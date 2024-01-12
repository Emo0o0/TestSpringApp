package com.example.testspringapp.core.exceptions.registercustomer;

public class CustomerBlankNameException extends RuntimeException{
    public CustomerBlankNameException(String message){
        super(message);
    }
}
