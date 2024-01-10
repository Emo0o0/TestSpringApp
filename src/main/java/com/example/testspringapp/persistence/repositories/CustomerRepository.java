package com.example.testspringapp.persistence.repositories;

import com.example.testspringapp.persistence.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
