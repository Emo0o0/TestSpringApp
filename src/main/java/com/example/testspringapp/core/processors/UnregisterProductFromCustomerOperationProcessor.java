package com.example.testspringapp.core.processors;

import com.example.testspringapp.api.inputoutput.unregisterproductfromcustomer.UnregisterProductFromCustomerInput;
import com.example.testspringapp.api.inputoutput.unregisterproductfromcustomer.UnregisterProductFromCustomerOperation;
import com.example.testspringapp.api.inputoutput.unregisterproductfromcustomer.UnregisterProductFromCustomerOutput;
import com.example.testspringapp.core.exceptions.registercustomer.CustomerInvalidEmailException;
import com.example.testspringapp.persistence.entities.Customer;
import com.example.testspringapp.persistence.entities.Product;
import com.example.testspringapp.persistence.repositories.CustomerRepository;
import com.example.testspringapp.persistence.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UnregisterProductFromCustomerOperationProcessor implements UnregisterProductFromCustomerOperation {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    public UnregisterProductFromCustomerOutput process(UnregisterProductFromCustomerInput unregisterProductFromCustomerInput) {

        Customer customer = customerRepository.findByEmail(unregisterProductFromCustomerInput.getCustomerEmail())
                .orElseThrow(() -> new CustomerInvalidEmailException("Customer does not exist"));

        for (Product p : unregisterProductFromCustomerInput.getProducts()) {
            customer.getProducts().remove(p);
        }
        customerRepository.save(customer);

        return UnregisterProductFromCustomerOutput.builder()
                .success(true)
                .build();
    }
}
