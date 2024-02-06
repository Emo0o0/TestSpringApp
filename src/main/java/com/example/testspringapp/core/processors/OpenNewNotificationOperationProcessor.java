package com.example.testspringapp.core.processors;

import com.example.testspringapp.api.inputoutput.opennewnotification.OpenNewNotificationInput;
import com.example.testspringapp.api.inputoutput.opennewnotification.OpenNewNotificationOperation;
import com.example.testspringapp.api.inputoutput.opennewnotification.OpenNewNotificationOutput;
import com.example.testspringapp.configs.LoggedUser;
import com.example.testspringapp.core.exceptions.login.UserNotFoundException;
import com.example.testspringapp.persistence.entities.Notification;
import com.example.testspringapp.persistence.entities.User;
import com.example.testspringapp.persistence.repositories.NotificationRepository;
import com.example.testspringapp.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpenNewNotificationOperationProcessor implements OpenNewNotificationOperation {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;


    @Override
    public OpenNewNotificationOutput process(OpenNewNotificationInput openNewNotificationInput) {

        User currentUser = userRepository.findByUsername(LoggedUser.getLoggedUser().getUsername())
                .orElseThrow(() -> new UserNotFoundException("Username not found"));

        for (Notification n : openNewNotificationInput.getNotifications()) {
            currentUser.getNotifications().remove(n);

            Notification oldNotification = Notification.builder()
                    .title(n.getTitle())
                    .message(n.getMessage())
                    .timestamp(n.getTimestamp())
                    .read(true)
                    .build();


            if (!notificationRepository.existsByMessageAndTitleAndRead(oldNotification.getMessage(),oldNotification.getTitle(),oldNotification.getRead())) {
                currentUser.getNotifications().add(oldNotification);
                notificationRepository.save(oldNotification);
                userRepository.save(currentUser);
            }

        }

        return OpenNewNotificationOutput.builder()
                .success(true)
                .build();


    }
}
