package com.example.testspringapp.api.inputoutput.searchnotification;

import com.example.testspringapp.api.base.OperationResult;
import com.example.testspringapp.persistence.entities.Notification;
import lombok.*;

import java.util.Set;


@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchNotificationResult implements OperationResult {

    private Set<Notification> filteredNotifications;
    private Boolean success;
}
