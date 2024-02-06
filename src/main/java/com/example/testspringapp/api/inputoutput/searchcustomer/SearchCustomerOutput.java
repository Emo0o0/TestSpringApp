package com.example.testspringapp.api.inputoutput.searchcustomer;

import com.example.testspringapp.api.base.OperationResult;
import com.example.testspringapp.persistence.entities.Customer;
import lombok.*;

import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchCustomerOutput implements OperationResult {

    private Set<Customer> filteredCustomers;
    private Boolean success;
}
