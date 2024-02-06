package com.example.testspringapp.persistence.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
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
    @Column(updatable = false)
    private Double originalAmortization;
    @Column
    private Integer scrappingCriteria;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "products")
    private Set<Customer> customers;

    @EqualsAndHashCode.Exclude
    @CreationTimestamp
    private Timestamp timestamp;


    public void scrapProduct() {
        this.amortization = 100d;
    }

    public void setAmortization(double value) {
        amortization = value;
    }

    public void changeTypeToMA() {
        this.productType = ProductType.MA;
    }

    @Override
    public String toString() {
        //return String.format("%-30s %-10s %-10.2f %-10d", title, productType.toString(), amortization, scrappingCriteria);
        return title;
    }
}
