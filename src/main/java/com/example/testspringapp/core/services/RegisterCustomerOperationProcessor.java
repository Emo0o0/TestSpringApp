package com.example.testspringapp.core.services;

import com.example.testspringapp.api.inputoutput.registercustomer.RegisterCustomerInput;
import com.example.testspringapp.api.inputoutput.registercustomer.RegisterCustomerOperation;
import com.example.testspringapp.api.inputoutput.registercustomer.RegisterCustomerOutput;
import com.example.testspringapp.core.exceptions.registercustomer.CustomerAlreadyExistsException;
import com.example.testspringapp.core.exceptions.registercustomer.CustomerBlankEmailException;
import com.example.testspringapp.core.exceptions.registercustomer.CustomerBlankNameException;
import com.example.testspringapp.core.exceptions.registercustomer.CustomerBlankPhoneException;
import com.example.testspringapp.persistence.entities.Customer;
import com.example.testspringapp.persistence.repositories.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterCustomerOperationProcessor implements RegisterCustomerOperation {

    private final CustomerRepository customerRepository;

    @Override
    public RegisterCustomerOutput process(RegisterCustomerInput registerCustomerInput) {

        validation(registerCustomerInput);

        if (customerRepository.existsByEmail(registerCustomerInput.getEmail())) {
            throw new CustomerAlreadyExistsException("This customer is already registered");
        }
        Customer customer = Customer.builder()
                .name(registerCustomerInput.getName())
                .phone(registerCustomerInput.getPhone())
                .email(registerCustomerInput.getEmail())
                .build();

        customerRepository.save(customer);

        return RegisterCustomerOutput.builder()
                .name(customer.getName())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .success(true)
                .build();

    }

    private boolean validation(RegisterCustomerInput input){

        if(input.getName().isBlank()){
            throw new CustomerBlankNameException("Name cannot be left blank");
        }
        if(input.getEmail().isBlank()){
            throw new CustomerBlankEmailException("Email cannot be left blank");
        }
        if(input.getPhone().isBlank()){
            throw new CustomerBlankPhoneException("Phone cannot be left blank");
        }
        return true;
    }
}
