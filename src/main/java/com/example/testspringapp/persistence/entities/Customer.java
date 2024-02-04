package com.example.testspringapp.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String phone;
    @Column (unique = true) @Email
    private String email;
    @Column
    @ManyToMany
    private Set<Product> products;


    public Set<Product> getProducts(){
        return new HashSet<>(products);
    }
    public boolean addProduct(Product product){
        return products.add(product);
    }
    public String toString(){
        return email;
    }
}
