package com.example.testspringapp.api.inputoutput.searchnotification;

import com.example.testspringapp.api.base.OperationInput;
import com.example.testspringapp.persistence.entities.Notification;
import lombok.*;

import java.util.Set;

@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchNotificationInput implements OperationInput {

    private Set<Notification> notifications;
    private String searchWord;

}
