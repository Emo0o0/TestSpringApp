package com.example.testspringapp.persistence.repositories;

import com.example.testspringapp.persistence.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Set<Notification> findAllByRead(Boolean newNotifications);
    //boolean existsByMessageAndTitle(String message, String title);
    boolean existsByMessageAndTitleAndRead(String message, String title, Boolean read);
}
