package com.example.testspringapp.entities;

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
    private long id;
    @Column
    private String description;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    @Column
    private Double amortization;
    @ManyToOne
    private User user;


}
