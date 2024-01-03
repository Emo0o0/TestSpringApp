package com.example.testspringapp.controllers;

import com.example.testspringapp.configs.FxmlView;
import com.example.testspringapp.configs.StageManager;
import com.example.testspringapp.persistence.entities.Customer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MRPHomeController {

    @FXML
    private Label registerProductLabel;
    @FXML
    private Label scrappingCriteriaLabel;
    @FXML
    private Label registerCustomerLabel;
    @FXML
    private Label addProductsLabel;
    @FXML
    private Label removeProductsLabel;
    @FXML
    private Label scrapProductLabel;
    @FXML
    private Label leaveLabel;

    private final StageManager stageManager;

    @Autowired
    @Lazy
    public MRPHomeController(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    public void registerProductHover(){
        registerProductLabel.setUnderline(true);
    }
    public void registerProductHoverExit(){
        registerProductLabel.setUnderline(false);
    }

    public void registerProduct() {

    }

    public void setScrappingCriteria() {

    }

    public void registerClient() {

    }

    public void addProductToClientCard() {

    }

    public void removeProductFromClientCard() {

    }

    public void scrapProduct() {

    }

    public void leave() {
        stageManager.switchScene(FxmlView.LOGIN);
    }

}
