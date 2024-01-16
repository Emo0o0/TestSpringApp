package com.example.testspringapp.controllers;

import com.example.testspringapp.configs.FxmlView;
import com.example.testspringapp.configs.StageManager;
import com.example.testspringapp.persistence.entities.Product;
import com.example.testspringapp.persistence.repositories.ProductRepository;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MRPRegisterProductToCustomerController {

    @FXML
    private ComboBox<String> chooseClient;
    @FXML
    private ComboBox<String> chooseProduct;
    @FXML
    private Button submit;
    @FXML
    private ListView<Product> products;
    private final StageManager stageManager;
    private final ProductRepository productRepository;

    @Autowired
    @Lazy
    public MRPRegisterProductToCustomerController(StageManager stageManager, ProductRepository productRepository) {
        this.stageManager = stageManager;
        this.productRepository = productRepository;
    }

    @FXML
    public void initialize() {
        List<Product> productList = productRepository.findAll();
//        products.getItems().addAll(productRepository.findAll());
        for (Product p : productList)
            products.getItems().add(p);
        products.getSelectionModel().selectAll();
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
