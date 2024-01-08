package com.example.testspringapp.api.inputoutput.registerproduct;

import com.example.testspringapp.api.base.OperationInput;
import com.example.testspringapp.persistence.entities.ProductType;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterProductInput implements OperationInput {

    private String title;
    private String description;
    private ProductType productType;
    private Double amortization;

}
