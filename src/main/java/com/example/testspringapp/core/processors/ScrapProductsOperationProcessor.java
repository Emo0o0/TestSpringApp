package com.example.testspringapp.core.processors;

import com.example.testspringapp.api.inputoutput.scrapproduct.ScrapProductsInput;
import com.example.testspringapp.api.inputoutput.scrapproduct.ScrapProductsOperation;
import com.example.testspringapp.api.inputoutput.scrapproduct.ScrapProductsOutput;
import com.example.testspringapp.core.exceptions.scrapproduct.ProductInCustomerCardException;
import com.example.testspringapp.persistence.entities.Product;
import com.example.testspringapp.persistence.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScrapProductsOperationProcessor implements ScrapProductsOperation {

    private final ProductRepository productRepository;
    @Override
    public ScrapProductsOutput process(ScrapProductsInput scrapProductsInput) {

        for(Product p : scrapProductsInput.getProductsToScrap()){
            if(!p.getCustomers().isEmpty()){
                throw new ProductInCustomerCardException("Product [" + p.getTitle()+ "] exists in one or more customer cards");
            }
            p.scrapProduct();
            productRepository.save(p);
        }

        return ScrapProductsOutput.builder()
                .success(true)
                .build();
    }
}
