package com.example.testspringapp.core.services;

import com.example.testspringapp.api.inputoutput.registerproduct.RegisterProductInput;
import com.example.testspringapp.api.inputoutput.registerproduct.RegisterProductOperation;
import com.example.testspringapp.api.inputoutput.registerproduct.RegisterProductOutput;
import com.example.testspringapp.core.exceptions.registerproduct.*;
import com.example.testspringapp.persistence.entities.Product;
import com.example.testspringapp.persistence.entities.ProductType;
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
                .productType(ProductType.valueOf(registerProductInput.getProductType()))
                .amortization(Double.parseDouble(registerProductInput.getAmortization()))
                .scrappingCriteria(Integer.parseInt(registerProductInput.getScrappingCriteria()))
                .build();

        productRepository.save(product);

        return RegisterProductOutput.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .productType(product.getProductType().toString())
                .amortization(product.getAmortization().toString())
                .scrappingCriteria(product.getScrappingCriteria().toString())
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
        if(input.getScrappingCriteria().isBlank()){
            throw new ProductBlankScrappingCriteriaException("Please enter scrapping criteria value");
        }

        try{
            Double.valueOf(input.getAmortization());
        }catch(Exception e){
            throw new ProductInvalidAmortizationValueException("Invalid amortization value");
        }
        if(Double.parseDouble(input.getAmortization())<0 || Double.parseDouble(input.getAmortization())>100){
            throw new ProductInvalidAmortizationValueException("Amortization value should be between 0 and 100");
        }

        try{
            Double.valueOf(input.getScrappingCriteria());
        }catch (Exception e){
            throw new ProductInvalidScrappingCriteriaValueException("Invalid scrapping criteria. Enter years");
        }
        if(Double.valueOf(input.getScrappingCriteria())<1){
            throw new ProductInvalidScrappingCriteriaValueException("Scrapping criteria cannot be under 1");
        }

        return true;
    }
}
