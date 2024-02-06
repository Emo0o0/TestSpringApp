package com.example.testspringapp.api.inputoutput.searchproduct;

import com.example.testspringapp.api.base.OperationInput;
import com.example.testspringapp.persistence.entities.Product;
import lombok.*;

import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchProductInput implements OperationInput {

    private Set<Product> products;
    private String searchWord;

}
