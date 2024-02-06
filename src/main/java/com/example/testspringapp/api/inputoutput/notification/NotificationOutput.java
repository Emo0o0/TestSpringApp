package com.example.testspringapp.api.inputoutput.notification;

import com.example.testspringapp.api.base.OperationResult;
import com.example.testspringapp.persistence.entities.Notification;
import lombok.*;

@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationOutput implements OperationResult {
    private Notification notification;
    private Boolean success;
}
