package com.example.testspringapp.persistence.repositories;

import com.example.testspringapp.persistence.entities.User;
import com.example.testspringapp.persistence.entities.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Set<User> findAllByUserType(UserType userType);
}
