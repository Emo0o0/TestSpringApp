package com.example.testspringapp.api.inputoutput.scrapproduct;

import com.example.testspringapp.api.base.OperationInput;
import com.example.testspringapp.persistence.entities.Product;
import lombok.*;

import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScrapProductsInput implements OperationInput {

    private Set<Product> productsToScrap;

}
