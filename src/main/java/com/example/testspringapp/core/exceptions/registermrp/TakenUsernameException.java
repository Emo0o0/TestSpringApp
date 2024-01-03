package com.example.testspringapp.core.exceptions.registermrp;

public class TakenUsernameException extends RuntimeException {
    public TakenUsernameException(String message) {
        super(message);
    }
}
