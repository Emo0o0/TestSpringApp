package com.example.testspringapp.core.processors;

import com.example.testspringapp.api.inputoutput.registerproducttocustomer.RegisterProductToCustomerInput;
import com.example.testspringapp.api.inputoutput.registerproducttocustomer.RegisterProductToCustomerOperation;
import com.example.testspringapp.api.inputoutput.registerproducttocustomer.RegisterProductToCustomerOutput;
import com.example.testspringapp.core.exceptions.registercustomer.CustomerInvalidEmailException;
import com.example.testspringapp.core.exceptions.registerproducttocustomer.CustomerNotChosenException;
import com.example.testspringapp.core.exceptions.registerproducttocustomer.ProductNotChosenException;
import com.example.testspringapp.persistence.entities.Customer;
import com.example.testspringapp.persistence.entities.Product;
import com.example.testspringapp.persistence.repositories.CustomerRepository;
import com.example.testspringapp.persistence.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterProductToCustomerOperationProcessor implements RegisterProductToCustomerOperation {

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;


    @Override
    @Transactional
    public RegisterProductToCustomerOutput process(RegisterProductToCustomerInput registerProductToCustomerInput) {

        validation(registerProductToCustomerInput);


        Customer tempCustomer;
        for (Customer c : registerProductToCustomerInput.getCustomers()) {
            tempCustomer = customerRepository.findByEmail(c.getEmail()).orElseThrow(() -> new CustomerInvalidEmailException("does not exist"));
            for (Product p : registerProductToCustomerInput.getProducts()) {
                tempCustomer.addProduct(p);
            }
            customerRepository.save(tempCustomer);
        }

        return RegisterProductToCustomerOutput.builder()
                .success(true)
                .build();
    }

    private Boolean validation(RegisterProductToCustomerInput input) {
        if (input.getProducts().isEmpty()) {
            throw new ProductNotChosenException("No product/s were chosen");
        }
        if (input.getCustomers().isEmpty()) {
            throw new CustomerNotChosenException("No customer/s were chosen");
        }
        return true;
    }
}
