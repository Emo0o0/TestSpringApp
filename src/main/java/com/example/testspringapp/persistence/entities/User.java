package com.example.testspringapp.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @Column
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Notification> notifications;

    public void notify(Notification notification){
        notifications.add(notification);
    }
}
