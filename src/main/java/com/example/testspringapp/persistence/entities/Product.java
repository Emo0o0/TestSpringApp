package com.example.testspringapp.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String description;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    @Column
    private Double amortization;



}
