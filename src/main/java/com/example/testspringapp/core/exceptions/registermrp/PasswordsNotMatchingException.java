package com.example.testspringapp.core.exceptions.registermrp;

public class PasswordsNotMatchingException extends RuntimeException{
    public PasswordsNotMatchingException(String message){
        super(message);
    }
}
