package com.example.testspringapp.core.exceptions.registermrp;

public class BlankUsernameException extends RuntimeException{
    public BlankUsernameException(String message){
        super(message);
    }
}
