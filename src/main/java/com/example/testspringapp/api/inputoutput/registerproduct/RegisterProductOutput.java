package com.example.testspringapp.api.inputoutput.registerproduct;

import com.example.testspringapp.api.base.OperationResult;
import com.example.testspringapp.entities.ProductType;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterProductOutput implements OperationResult {

    private long id;
    private String description;
    private ProductType productType;
    private Double amortization;
}
