package com.example.testspringapp.controllers;

import com.example.testspringapp.configs.FxmlView;
import com.example.testspringapp.configs.StageManager;
import com.example.testspringapp.persistence.entities.Customer;
import com.example.testspringapp.persistence.entities.Product;
import com.example.testspringapp.persistence.entities.ProductType;
import com.example.testspringapp.persistence.repositories.CustomerRepository;
import com.example.testspringapp.persistence.repositories.ProductRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MRPRecordsClientsAndProductsController {

    @FXML
    private Label clientsAndProducts;
    @FXML
    private Label allProducts;

    @FXML
    private ListView<Customer> customersListView;
    private final Set<Customer> allCustomersSet = new HashSet<>();
    @FXML
    private TextArea customerDescription;
    @FXML
    private ListView<Product> productsListView;
    private final Set<Product> allProductsSet = new HashSet<>();
    @FXML
    private TextArea productDescription;
    @FXML
    private TextField productSearch;
    @FXML
    private TextField customerSearch;

    private final StageManager stageManager;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;


    @Lazy
    public MRPRecordsClientsAndProductsController(StageManager stageManager, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.stageManager = stageManager;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @FXML
    public void initialize(){
        allCustomersSet.clear();
        customersListView.getItems().addAll(customerRepository.findAll());
        allCustomersSet.addAll(customersListView.getItems());

        productsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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

    public void showAllProducts(){
        stageManager.switchScene(FxmlView.MRP_VIEW_RECORDS_ALL_PRODUCTS);
    }


}
