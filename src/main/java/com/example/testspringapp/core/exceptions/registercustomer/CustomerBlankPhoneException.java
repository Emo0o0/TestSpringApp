package com.example.testspringapp.core.exceptions.registercustomer;

public class CustomerBlankPhoneException extends RuntimeException{
    public CustomerBlankPhoneException(String message){
        super(message);
    }
}
