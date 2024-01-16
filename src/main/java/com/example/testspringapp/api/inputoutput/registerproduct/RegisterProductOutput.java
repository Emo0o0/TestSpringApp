package com.example.testspringapp.api.inputoutput.registerproduct;

import com.example.testspringapp.api.base.OperationResult;
import com.example.testspringapp.persistence.entities.ProductType;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterProductOutput implements OperationResult {

    private long id;
    private String title;
    private String description;
    private String productType;
    private String amortization;
    private String scrappingCriteria;
    private boolean success;
}
