package com.example.testspringapp.core.exceptions.registermrp;

public class BlankPasswordException extends RuntimeException{
    public BlankPasswordException(String message){
        super(message);
    }
}
