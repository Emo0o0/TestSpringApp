package com.example.testspringapp.repositories;

import com.example.testspringapp.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByAmortization(Double id);

}
