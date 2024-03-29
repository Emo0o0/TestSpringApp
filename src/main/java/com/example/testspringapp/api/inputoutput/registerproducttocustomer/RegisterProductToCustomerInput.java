package com.example.testspringapp.api.inputoutput.registerproducttocustomer;

import com.example.testspringapp.api.base.OperationInput;
import com.example.testspringapp.persistence.entities.Customer;
import com.example.testspringapp.persistence.entities.Product;
import lombok.*;

import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterProductToCustomerInput implements OperationInput {


    private Set<Product> products;
    private Set<Customer> customers;

}
