package com.example.testspringapp.controllers;

import com.example.testspringapp.configs.FxmlView;
import com.example.testspringapp.configs.LoggedUser;
import com.example.testspringapp.configs.StageManager;
import com.example.testspringapp.persistence.entities.Customer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MRPHomeController  {
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

    //region MouseHover
    public void registerProductHover(){
        registerProductLabel.setUnderline(true);
    }
    public void registerProductHoverExit(){
        registerProductLabel.setUnderline(false);
    }

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

    public void registerProduct() {
        stageManager.switchScene(FxmlView.MRP_HOME_REGISTER_PRODUCT);
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
