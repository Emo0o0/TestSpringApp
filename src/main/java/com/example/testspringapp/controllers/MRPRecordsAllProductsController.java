package com.example.testspringapp.controllers;

import com.example.testspringapp.configs.FxmlView;
import com.example.testspringapp.configs.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MRPRecordsAllProductsController {

    @FXML
    public Label clientsAndProducts;
    @FXML
    public Label allProducts;

    private final StageManager stageManager;
    @Lazy
    public MRPRecordsAllProductsController(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    @FXML
    public void initialize(){

    }

    public void registerProduct() {
        stageManager.switchScene(FxmlView.MRP_HOME_REGISTER_PRODUCT);
    }

    public void addProductToClientCard() {
        stageManager.switchScene(FxmlView.MRP_REGISTER_PRODUCT_TO_CUSTOMER);
    }

    public void registerCustomer() {
        stageManager.switchScene(FxmlView.MRP_HOME_REGISTER_CUSTOMER);
    }

    public void removeProductFromClientCard() {
        stageManager.switchScene(FxmlView.MRP_UNREGISTER_PRODUCT_FROM_CUSTOMER);
    }

    public void scrapProduct() {
        stageManager.switchScene(FxmlView.MRP_SCRAP_PRODUCTS);
    }

    public void leave() {
        stageManager.switchScene(FxmlView.LOGIN);
    }

    public void showClientsAndProducts(){
        stageManager.switchScene(FxmlView.MRP_VIEW_RECORDS_CLIENTS_AND_PRODUCTS);
    }
}
