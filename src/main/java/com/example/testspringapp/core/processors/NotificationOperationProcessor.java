package com.example.testspringapp.core.processors;

import com.example.testspringapp.api.inputoutput.notification.NotificationInput;
import com.example.testspringapp.api.inputoutput.notification.NotificationOperation;
import com.example.testspringapp.api.inputoutput.notification.NotificationOutput;
import com.example.testspringapp.persistence.entities.Notification;
import com.example.testspringapp.persistence.entities.User;
import com.example.testspringapp.persistence.entities.UserType;
import com.example.testspringapp.persistence.repositories.NotificationRepository;
import com.example.testspringapp.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class NotificationOperationProcessor implements NotificationOperation {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    @Override
    public NotificationOutput process(NotificationInput notificationInput) {

        String sb = "Title: " + notificationInput.getProduct().getTitle() + "\n" +
                "Description: " + notificationInput.getProduct().getDescription() + "\n" +
                "Product type: " + notificationInput.getProduct().getProductType() + "\n" +
                "Amortization: " + notificationInput.getProduct().getAmortization() + "\n" +
                "Scrapping criteria: " + notificationInput.getProduct().getScrappingCriteria() + " years\n" +
                "Date registered: " + new Date(notificationInput.getProduct().getTimestamp().getTime()) + "\n" +
                "\n";

        Notification notification = Notification.builder()
                .title("Product [" + notificationInput.getProduct().getTitle() + "] "+notificationInput.getProductChange())
                .message(sb)
                .read(false)
                .productId(notificationInput.getProduct().getId().toString())
                .build();

        notificationRepository.save(notification);

        Set<User> mrpUsers = userRepository.findAllByUserType(UserType.MRP);
        for (User u : mrpUsers) {
            u.notify(notification);
            userRepository.save(u);
        }

        return NotificationOutput.builder()
                .notification(notification)
                .success(true)
                .build();


    }
}
