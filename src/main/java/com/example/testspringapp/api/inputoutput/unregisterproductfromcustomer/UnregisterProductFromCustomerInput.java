package com.example.testspringapp.api.inputoutput.unregisterproductfromcustomer;

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
public class UnregisterProductFromCustomerInput implements OperationInput {

    private String customerEmail;
    private Set<Product> products;

}
