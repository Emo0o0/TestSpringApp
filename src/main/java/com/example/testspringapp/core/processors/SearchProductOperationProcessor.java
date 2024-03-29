package com.example.testspringapp.core.processors;

import com.example.testspringapp.api.inputoutput.searchproduct.SearchProductInput;
import com.example.testspringapp.api.inputoutput.searchproduct.SearchProductOperation;
import com.example.testspringapp.api.inputoutput.searchproduct.SearchProductOutput;
import com.example.testspringapp.persistence.entities.Product;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SearchProductOperationProcessor implements SearchProductOperation {
    @Override
    public SearchProductOutput process(SearchProductInput searchProductInput) {

        Set<Product> filteredProducts = new HashSet<>();
        for (Product p : searchProductInput.getProducts()) {
            if (p.getTitle().toLowerCase().contains(searchProductInput.getSearchWord().toLowerCase())
                    || p.getDescription().toLowerCase().contains(searchProductInput.getSearchWord().toLowerCase())
                    || p.getProductType().toString().equalsIgnoreCase(searchProductInput.getSearchWord())) {
                filteredProducts.add(p);
            }
        }
        return SearchProductOutput.builder()
                .filteredProducts(filteredProducts)
                .success(true)
                .build();
    }
}
