package com.example.testspringapp.api.inputoutput.opennewnotification;

import com.example.testspringapp.api.base.OperationInput;
import com.example.testspringapp.persistence.entities.Notification;
import lombok.*;

import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpenNewNotificationInput implements OperationInput {

    private Set<Notification> notifications;

}
