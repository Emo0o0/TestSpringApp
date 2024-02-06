package com.example.testspringapp.core.processors;

import com.example.testspringapp.api.inputoutput.deletenotification.DeleteNotificationInput;
import com.example.testspringapp.api.inputoutput.deletenotification.DeleteNotificationOperation;
import com.example.testspringapp.api.inputoutput.deletenotification.DeleteNotificationOutput;
import com.example.testspringapp.configs.LoggedUser;
import com.example.testspringapp.core.exceptions.login.UserNotFoundException;
import com.example.testspringapp.persistence.entities.Notification;
import com.example.testspringapp.persistence.entities.User;
import com.example.testspringapp.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteNotificationOperationProcessor implements DeleteNotificationOperation {

    private final UserRepository userRepository;

    @Override
    public DeleteNotificationOutput process(DeleteNotificationInput deleteNotificationInput) {

        User currentUser = userRepository.findByUsername(LoggedUser.getLoggedUser().getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        for (Notification n : deleteNotificationInput.getNotificationsToDelete()) {
            currentUser.getNotifications().remove(n);
        }
        userRepository.save(currentUser);

        return DeleteNotificationOutput.builder()
                .success(true)
                .build();
    }
}
