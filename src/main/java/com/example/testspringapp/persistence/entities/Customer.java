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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "customers_products",
            joinColumns = {@JoinColumn(name="customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private Set<Product> products;

    public boolean addProduct(Product product){
        return products.add(product);
    }
    public boolean removeProduct(Product product){
        return products.remove(product);
    }
    public String toString(){
        return email;
    }
}
