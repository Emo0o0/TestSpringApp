package com.example.testspringapp.api.inputoutput.searchnotification;

import com.example.testspringapp.persistence.entities.Notification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SearchNotificationOperationProcessor implements SearchNotificationOperation {

    @Override
    public SearchNotificationResult process(SearchNotificationInput searchNotificationInput) {

        Set<Notification> filteredNotifications=new HashSet<>();
        for(Notification n : searchNotificationInput.getNotifications()){
            if(n.getTitle().toLowerCase().contains(searchNotificationInput.getSearchWord().toLowerCase())){
                filteredNotifications.add(n);
            }
        }

        return SearchNotificationResult.builder()
                .filteredNotifications(filteredNotifications)
                .success(true)
                .build();
    }
}
