package com.example.testspringapp.core.processors;

import com.example.testspringapp.api.inputoutput.registerproducttocustomer.searchcustomer.SearchCustomerInput;
import com.example.testspringapp.api.inputoutput.registerproducttocustomer.searchcustomer.SearchCustomerOperation;
import com.example.testspringapp.api.inputoutput.registerproducttocustomer.searchcustomer.SearchCustomerOutput;
import com.example.testspringapp.persistence.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SearchCustomerOperationProcessor implements SearchCustomerOperation {
    @Override
    public SearchCustomerOutput process(SearchCustomerInput searchCustomerInput) {

        Set<Customer> filteredCustomers = new HashSet<>();
        for (Customer c : searchCustomerInput.getCustomers()) {
            if (c.getEmail().toLowerCase().contains(searchCustomerInput.getSearchWord().toLowerCase())) {
                filteredCustomers.add(c);
            }
        }

        return SearchCustomerOutput.builder()
                .filteredCustomers(filteredCustomers)
                .success(true)
                .build();

    }
}
