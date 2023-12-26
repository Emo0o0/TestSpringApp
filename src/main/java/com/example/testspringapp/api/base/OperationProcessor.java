package com.example.testspringapp.api.base;

public interface OperationProcessor <Input, Output>{
    Output process(Input input);
}
