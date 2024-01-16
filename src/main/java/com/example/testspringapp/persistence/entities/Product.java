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
    private String title;
    @Column
    private String description;
    @Enumerated(EnumType.STRING)
    private ProductType productType;
    @Column
    private Double amortization;
    @Column
    private Integer scrappingCriteria;


    @Override
    public String toString() {
        return String.format("%-30s %-10s %-10.2f %-10d", title, productType.toString(), amortization, scrappingCriteria);
    }
}
