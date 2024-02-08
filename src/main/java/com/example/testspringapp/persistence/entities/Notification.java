package com.example.testspringapp.persistence.entities;

import com.example.testspringapp.persistence.repositories.UserRepository;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "notifications")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Long id;
    private String title;
    private String message;
    private Boolean read;
    @CreationTimestamp
    private Timestamp timestamp;
    @EqualsAndHashCode.Exclude
    private String productId;


    public String toString() {
        return title + "\t\t" + new Date(timestamp.getTime());
    }

    public void read() {
        this.read = true;
    }

}
