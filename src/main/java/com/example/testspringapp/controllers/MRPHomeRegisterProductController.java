package com.example.testspringapp.controllers;

import com.example.testspringapp.configs.FxmlView;
import com.example.testspringapp.configs.StageManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MRPHomeRegisterProductController {


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
    private ComboBox<String> productTypes;
    @FXML
    private Button submit;
    private final StageManager stageManager;

    @Autowired
    @Lazy
    public MRPHomeRegisterProductController(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    @FXML
    public void initialize(){
        showProductTypes();
    }

    //region MouseHover
    public void registerCustomerHover(MouseEvent mouseEvent) {
        registerCustomerLabel.setUnderline(true);
    }

    public void registerCustomerHoverExit(MouseEvent mouseEvent) {
        registerCustomerLabel.setUnderline(false);
    }

    public void addProductHover(MouseEvent mouseEvent) {
        addProductsLabel.setUnderline(true);
    }

    public void addProductHoverExit(MouseEvent mouseEvent) {
        addProductsLabel.setUnderline(false);
    }

    public void removeProductHover(MouseEvent mouseEvent) {
        removeProductsLabel.setUnderline(true);
    }

    public void removeProductHoverExit(MouseEvent mouseEvent) {
        removeProductsLabel.setUnderline(false);
    }

    public void leaveHover(MouseEvent mouseEvent) {
        leaveLabel.setUnderline(true);
    }

    public void leaveHoverExit(MouseEvent mouseEvent) {
        leaveLabel.setUnderline(false);
    }

    public void scrapCriteriaHover(MouseEvent mouseEvent) {
        scrappingCriteriaLabel.setUnderline(true);
    }

    public void scrapCriteriaHoverExit(MouseEvent mouseEvent) {
        scrappingCriteriaLabel.setUnderline(false);
    }

    public void scrapProductHover(MouseEvent mouseEvent) {
        scrapProductLabel.setUnderline(true);
    }

    public void scrapProductHoverExit(MouseEvent mouseEvent) {
        scrapProductLabel.setUnderline(false);
    }
    //endregion

    public void showProductTypes(){
        productTypes.getItems().add("DMA");
        productTypes.getItems().add("MA");
        productTypes.setVisibleRowCount(2);
    }

    public void setScrappingCriteria() {

    }

    public void registerCustomer() {
        stageManager.switchScene(FxmlView.MRP_HOME_REGISTER_CUSTOMER);
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
