package com.example.testspringapp.core.services;

import com.example.testspringapp.api.inputoutput.registerproduct.RegisterProductInput;
import com.example.testspringapp.api.inputoutput.registerproduct.RegisterProductOperation;
import com.example.testspringapp.api.inputoutput.registerproduct.RegisterProductOutput;
import com.example.testspringapp.persistence.entities.Product;
import com.example.testspringapp.persistence.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterProductOperationProcessor implements RegisterProductOperation {

    private final ProductRepository productRepository;

    @Override
    public RegisterProductOutput process(RegisterProductInput registerProductInput) {

        Product product = Product.builder()
                .title(registerProductInput.getTitle())
                .description(registerProductInput.getDescription())
                .productType(registerProductInput.getProductType())
                .amortization(registerProductInput.getAmortization())
                .build();


        productRepository.save(product);

        return RegisterProductOutput.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .productType(product.getProductType())
                .amortization(product.getAmortization())
                .success(true)
                .build();
    }
}
