package com.example.testspringapp.controllers;

import com.example.testspringapp.configs.FxmlView;
import com.example.testspringapp.configs.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MRPHomeRegisterCustomerController {

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
    private Label submit;
    private final StageManager stageManager;

    @Autowired
    @Lazy
    public MRPHomeRegisterCustomerController(StageManager stageManager) {
        this.stageManager = stageManager;
    }

    @FXML
    public void initialize(){
        showProductTypes();
    }

    //region MouseHover
    public void submitHover(){
        submit.setTextFill(Color.WHITE);
    }
    public void submitHoverExit(){
        submit.setTextFill(Color.web("#942929"));
    }
    public void registerProductHover(MouseEvent mouseEvent) {
        registerProductLabel.setUnderline(true);
    }

    public void registerProductHoverExit(MouseEvent mouseEvent) {
        registerProductLabel.setUnderline(false);
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
