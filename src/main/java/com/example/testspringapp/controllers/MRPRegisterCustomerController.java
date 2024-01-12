package com.example.testspringapp.controllers;

import com.example.testspringapp.api.inputoutput.registercustomer.RegisterCustomerInput;
import com.example.testspringapp.api.inputoutput.registercustomer.RegisterCustomerOperation;
import com.example.testspringapp.configs.FxmlView;
import com.example.testspringapp.configs.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MRPRegisterCustomerController {

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
    private TextField clientName;
    @FXML
    private TextField clientEmail;
    @FXML
    private TextField clientPhone;
    @FXML
    private Button submit;
    private final StageManager stageManager;
    private final RegisterCustomerOperation registerCustomerOperation;

    @Autowired
    @Lazy
    public MRPRegisterCustomerController(StageManager stageManager, RegisterCustomerOperation registerCustomerOperation) {
        this.stageManager = stageManager;
        this.registerCustomerOperation = registerCustomerOperation;
    }

    @FXML
    public void initialize() {
    }



    public void registerCustomer() {
        RegisterCustomerInput input = RegisterCustomerInput.builder()
                .name(clientName.getText())
                .phone(clientPhone.getText())
                .email(clientEmail.getText())
                .build();
        registerCustomerOperation.process(input);
    }

    public void registerProduct() {
        stageManager.switchScene(FxmlView.MRP_HOME_REGISTER_PRODUCT);
    }

    public void setScrappingCriteria() {

    }

    public void addProductToClientCard() {
        stageManager.switchScene(FxmlView.MRP_REGISTER_PRODUCT_TO_CUSTOMER);
    }

    public void removeProductFromClientCard() {

    }

    public void scrapProduct() {

    }

    public void leave() {
        stageManager.switchScene(FxmlView.LOGIN);
    }

}
