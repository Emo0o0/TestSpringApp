package com.example.testspringapp.persistence.repositories;

import com.example.testspringapp.persistence.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByAmortization(Double id);

}
