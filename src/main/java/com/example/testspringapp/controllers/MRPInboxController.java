package com.example.testspringapp.controllers;

import com.example.testspringapp.api.inputoutput.deletenotification.DeleteNotificationInput;
import com.example.testspringapp.api.inputoutput.deletenotification.DeleteNotificationOperation;
import com.example.testspringapp.api.inputoutput.opennewnotification.OpenNewNotificationInput;
import com.example.testspringapp.api.inputoutput.opennewnotification.OpenNewNotificationOperation;
import com.example.testspringapp.api.inputoutput.searchnotification.SearchNotificationInput;
import com.example.testspringapp.api.inputoutput.searchnotification.SearchNotificationOperation;
import com.example.testspringapp.configs.FxmlView;
import com.example.testspringapp.configs.LoggedUser;
import com.example.testspringapp.configs.StageManager;
import com.example.testspringapp.core.exceptions.inbox.NotificationNotSelectedException;
import com.example.testspringapp.core.exceptions.login.UserNotFoundException;
import com.example.testspringapp.persistence.entities.Notification;
import com.example.testspringapp.persistence.entities.User;
import com.example.testspringapp.persistence.repositories.UserRepository;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MRPInboxController {

    @FXML
    private ListView<Notification> newNotifications;
    private Set<Notification> allNewNotifications = new HashSet<>();
    @FXML
    private ListView<Notification> oldNotifications;
    private Set<Notification> allOldNotifications = new HashSet<>();
    @FXML
    private TextArea notificationMessage;
    @FXML
    private TextField oldNotificationSearch;
    @FXML
    private TextField newNotificationSearch;

    private final StageManager stageManager;
    private final UserRepository userRepository;
    private final SearchNotificationOperation searchNotificationOperation;
    private final OpenNewNotificationOperation openNewNotificationOperation;
    private final DeleteNotificationOperation deleteNotificationOperation;

    @Lazy
    public MRPInboxController(StageManager stageManager, UserRepository userRepository, SearchNotificationOperation searchNotificationOperation, OpenNewNotificationOperation openNewNotificationOperation, DeleteNotificationOperation deleteNotificationOperation) {
        this.stageManager = stageManager;
        this.userRepository = userRepository;
        this.searchNotificationOperation = searchNotificationOperation;
        this.openNewNotificationOperation = openNewNotificationOperation;
        this.deleteNotificationOperation = deleteNotificationOperation;
    }

    @FXML
    public void initialize() {

        loadFXMLFields();
    }

    private void loadFXMLFields() {

        oldNotifications.getItems().clear();
        newNotifications.getItems().clear();
        allOldNotifications.clear();
        allNewNotifications.clear();

        oldNotifications.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        User currentUser = userRepository.findByUsername(LoggedUser.getLoggedUser().getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        for (Notification n : currentUser.getNotifications()) {
            if (n.getRead()) {
                oldNotifications.getItems().add(n);
            } else {
                newNotifications.getItems().add(n);
            }
        }
        allOldNotifications.addAll(oldNotifications.getItems());
        allNewNotifications.addAll(newNotifications.getItems());
    }

    public void registerProduct() {
        stageManager.switchScene(FxmlView.MRP_HOME_REGISTER_PRODUCT);
    }

    public void registerCustomer() {
        stageManager.switchScene(FxmlView.MRP_HOME_REGISTER_CUSTOMER);
    }


    public void addProductToClientCard() {
        stageManager.switchScene(FxmlView.MRP_REGISTER_PRODUCT_TO_CUSTOMER);
    }

    public void removeProductFromClientCard() {
        stageManager.switchScene(FxmlView.MRP_UNREGISTER_PRODUCT_FROM_CUSTOMER);
    }

    public void scrapProduct() {
        stageManager.switchScene(FxmlView.MRP_SCRAP_PRODUCTS);
    }

    public void viewRecords() {
        stageManager.switchScene(FxmlView.MRP_VIEW_RECORDS_CLIENTS_AND_PRODUCTS);
    }

    public void leave() {
        stageManager.switchScene(FxmlView.LOGIN);
    }


    public void filterNewNotifications() {
        newNotifications.getItems().clear();
        SearchNotificationInput input = SearchNotificationInput.builder()
                .notifications(allNewNotifications)
                .searchWord(newNotificationSearch.getText())
                .build();
        newNotifications.getItems().addAll(searchNotificationOperation.process(input).getFilteredNotifications());
    }

    public void filterOldNotifications() {
        oldNotifications.getItems().clear();
        SearchNotificationInput input = SearchNotificationInput.builder()
                .notifications(allOldNotifications)
                .searchWord(oldNotificationSearch.getText())
                .build();
        oldNotifications.getItems().addAll(searchNotificationOperation.process(input).getFilteredNotifications());
    }

    public void listViewChooseOldNotification() {

        if (oldNotifications.getSelectionModel().getSelectedItem() == null) {
            throw new NotificationNotSelectedException("No selection");
        }

        notificationMessage.setText("");
        for(Notification n : oldNotifications.getSelectionModel().getSelectedItems()){
            notificationMessage.setText(notificationMessage.getText()+"\n"+n.getMessage());
        }

    }

    public void listViewChooseNewNotification() {

        if (newNotifications.getSelectionModel().getSelectedItem() == null) {
            throw new NotificationNotSelectedException("No selection");
        }

        OpenNewNotificationInput input = OpenNewNotificationInput.builder()
                .notifications(new HashSet<>(newNotifications.getSelectionModel().getSelectedItems()))
                .build();

        openNewNotificationOperation.process(input);

        notificationMessage.setText(newNotifications.getSelectionModel().getSelectedItem().getMessage());

    }

    public void clearOldNotifications() {
        DeleteNotificationInput input = DeleteNotificationInput.builder()
                .notificationsToDelete(new HashSet<>(oldNotifications.getSelectionModel().getSelectedItems()))
                .build();

        deleteNotificationOperation.process(input);
        loadFXMLFields();
        notificationMessage.clear();
    }
}
