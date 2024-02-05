package com.example.testspringapp.persistence.repositories;

import com.example.testspringapp.persistence.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByAmortization(Double id);
    Set<Product> findAllByAmortizationLessThan(Double amount);

}
