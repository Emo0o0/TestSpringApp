package com.example.testspringapp.operations;

import com.example.testspringapp.api.inputoutput.registerproduct.RegisterProductInput;
import com.example.testspringapp.api.inputoutput.registerproduct.RegisterProductOperation;
import com.example.testspringapp.api.inputoutput.registerproduct.RegisterProductOutput;
import com.example.testspringapp.entities.Product;
import com.example.testspringapp.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterProductOperationProcessor implements RegisterProductOperation {

    @Autowired
    private final ProductRepository productRepository;

    @Override
    public RegisterProductOutput process(RegisterProductInput registerProductInput) {

        Product product = Product.builder()
                .productType(registerProductInput.getProductType())
                .description(registerProductInput.getDescription())
                .amortization(registerProductInput.getAmortization())
                .build();


        productRepository.save(product);

        RegisterProductOutput output = RegisterProductOutput.builder()
                .id(product.getId())
                .productType(product.getProductType())
                .description(product.getDescription())
                .amortization(product.getAmortization())
                .build();

        return output;
    }
}
