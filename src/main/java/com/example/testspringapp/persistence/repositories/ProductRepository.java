package com.example.testspringapp.persistence.repositories;

import com.example.testspringapp.persistence.entities.Product;
import com.example.testspringapp.persistence.entities.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Set<Product> findByAmortization(Double amount);
    Set<Product> findAllByAmortizationLessThan(Double amount);
    Set<Product> findAllByProductType(ProductType productType);

}
