package com.example.testspringapp.api.inputoutput.deletenotification;

import com.example.testspringapp.api.base.OperationInput;
import com.example.testspringapp.persistence.entities.Notification;
import lombok.*;

import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeleteNotificationInput implements OperationInput {

    private Set<Notification> notificationsToDelete;

}
