package com.example.testspringapp.controllers;

import com.example.testspringapp.configs.FxmlView;
import com.example.testspringapp.configs.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MRPRegisterProductToCustomerController {

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
    @FXML
    private ComboBox<String> chooseClient;
    @FXML
    private ComboBox<String> chooseProduct;
    @FXML
    private Button submit;
    private final StageManager stageManager;

    @Autowired
    @Lazy
    public MRPRegisterProductToCustomerController(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    @FXML
    public void initialize(){

    }

    public void registerProduct() {
        stageManager.switchScene(FxmlView.MRP_HOME_REGISTER_PRODUCT);
    }

    public void setScrappingCriteria() {

    }

    public void registerCustomer() {
        stageManager.switchScene(FxmlView.MRP_HOME_REGISTER_CUSTOMER);
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
