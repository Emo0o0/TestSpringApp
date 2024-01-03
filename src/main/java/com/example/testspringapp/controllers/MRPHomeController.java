package com.example.testspringapp.controllers;

import com.example.testspringapp.configs.StageManager;
import com.example.testspringapp.persistence.entities.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MRPHomeController {

    @FXML
    private ListView<Customer> listView;

    private final StageManager stageManager;

    @Autowired
    @Lazy
    public MRPHomeController(StageManager stageManager) {
        this.stageManager = stageManager;
    }
}
