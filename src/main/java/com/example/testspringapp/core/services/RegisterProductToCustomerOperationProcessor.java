package com.example.testspringapp.core.services;

import com.example.testspringapp.api.inputoutput.registerproducttocustomer.RegisterProductToCustomerInput;
import com.example.testspringapp.api.inputoutput.registerproducttocustomer.RegisterProductToCustomerOperation;
import com.example.testspringapp.api.inputoutput.registerproducttocustomer.RegisterProductToCustomerOutput;
import com.example.testspringapp.persistence.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterProductToCustomerOperationProcessor implements RegisterProductToCustomerOperation {

    private final ProductRepository productRepository;


    @Override
    public RegisterProductToCustomerOutput process(RegisterProductToCustomerInput registerProductToCustomerInput) {
        return null;
    }
}
