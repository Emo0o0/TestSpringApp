package com.example.testspringapp.api.inputoutput.searchcustomer;

import com.example.testspringapp.api.base.OperationInput;
import com.example.testspringapp.persistence.entities.Customer;
import lombok.*;

import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchCustomerInput implements OperationInput {

    private Set<Customer> customers;
    private String searchWord;

}
