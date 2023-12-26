package com.example.testspringapp.first;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class SomeResourceController {

    @FXML
    private TextField userField;
    @FXML
    private Button button;
    @FXML
    private Label label;

    @Value("classpath:secondResource.fxml")
    private Resource testResource;
    private String applicationTitle;
    private ApplicationContext applicationContext;

    public SomeResourceController(@Value("${spring.application.next.title}")String applicationTitle, ApplicationContext applicationContext) {
        this.applicationTitle = applicationTitle;
        this.applicationContext = applicationContext;
    }

    public void buttonPress() throws IOException {

        label.setText(userField.getText());


    }

}
