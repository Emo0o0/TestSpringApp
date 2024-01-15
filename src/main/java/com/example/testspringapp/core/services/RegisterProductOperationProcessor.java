package com.example.testspringapp.core.services;

import com.example.testspringapp.api.inputoutput.registerproduct.RegisterProductInput;
import com.example.testspringapp.api.inputoutput.registerproduct.RegisterProductOperation;
import com.example.testspringapp.api.inputoutput.registerproduct.RegisterProductOutput;
import com.example.testspringapp.core.exceptions.registerproduct.ProductBlankAmortizationException;
import com.example.testspringapp.core.exceptions.registerproduct.ProductBlankDescriptionException;
import com.example.testspringapp.core.exceptions.registerproduct.ProductBlankTitleException;
import com.example.testspringapp.core.exceptions.registerproduct.ProductInvalidAmortizationValueException;
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

        validation(registerProductInput);

        Product product = Product.builder()
                .title(registerProductInput.getTitle())
                .description(registerProductInput.getDescription())
                .productType(registerProductInput.getProductType())
                .amortization(Double.parseDouble(registerProductInput.getAmortization()))
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

    private boolean validation(RegisterProductInput input){
        if(input.getTitle().isBlank()){
            throw new ProductBlankTitleException("Title cannot be left blank");
        }
        if(input.getDescription().isBlank()){
            throw new ProductBlankDescriptionException("Description cannot be left blank");
        }
        if(input.getAmortization().isBlank()){
            throw new ProductBlankAmortizationException("Please enter an amortization value");
        }
        if(Double.parseDouble(input.getAmortization())<0 || Double.parseDouble(input.getAmortization())>100){
            throw new ProductInvalidAmortizationValueException("Amortization value should be between 0 and 100");
        }
        return true;
    }
}
