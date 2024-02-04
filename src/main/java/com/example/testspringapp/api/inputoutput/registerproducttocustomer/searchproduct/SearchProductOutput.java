package com.example.testspringapp.api.inputoutput.registerproducttocustomer.searchproduct;

import com.example.testspringapp.api.base.OperationResult;
import com.example.testspringapp.persistence.entities.Product;
import lombok.*;

import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchProductOutput implements OperationResult {

    private Set<Product> filteredProducts;
    private Boolean success;

}
