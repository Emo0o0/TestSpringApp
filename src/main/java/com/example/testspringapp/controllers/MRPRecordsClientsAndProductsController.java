package com.example.testspringapp.controllers;

import com.example.testspringapp.api.inputoutput.searchcustomer.SearchCustomerInput;
import com.example.testspringapp.api.inputoutput.searchcustomer.SearchCustomerOperation;
import com.example.testspringapp.api.inputoutput.searchproduct.SearchProductInput;
import com.example.testspringapp.api.inputoutput.searchproduct.SearchProductOperation;
import com.example.testspringapp.configs.FxmlView;
import com.example.testspringapp.configs.StageManager;
import com.example.testspringapp.core.exceptions.registercustomer.CustomerInvalidEmailException;
import com.example.testspringapp.persistence.entities.Customer;
import com.example.testspringapp.persistence.entities.Product;
import com.example.testspringapp.persistence.repositories.CustomerRepository;
import com.example.testspringapp.persistence.repositories.ProductRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Date;
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
    private final SearchCustomerOperation searchCustomerOperation;
    private final SearchProductOperation searchProductOperation;


    @Lazy
    public MRPRecordsClientsAndProductsController(StageManager stageManager, ProductRepository productRepository, CustomerRepository customerRepository, SearchCustomerOperation searchCustomerOperation, SearchProductOperation searchProductOperation) {
        this.stageManager = stageManager;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.searchCustomerOperation = searchCustomerOperation;
        this.searchProductOperation = searchProductOperation;
    }

    @FXML
    public void initialize() {
        allCustomersSet.clear();
        customersListView.getItems().addAll(customerRepository.findAll());
        allCustomersSet.addAll(customersListView.getItems());

        //allProductsSet.clear();
        productsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //allProductsSet.addAll(productsListView.getItems());
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

    public void viewInbox(){
        stageManager.switchScene(FxmlView.MRP_VIEW_INBOX);
    }
    public void leave() {
        stageManager.switchScene(FxmlView.LOGIN);
    }

    public void showAllProducts() {
        stageManager.switchScene(FxmlView.MRP_VIEW_RECORDS_ALL_PRODUCTS);
    }


    public void listViewChooseCustomer() {
        Customer c = customerRepository.findByEmail(customersListView.getSelectionModel().getSelectedItem().getEmail()).orElseThrow(() -> new CustomerInvalidEmailException("Customer not found"));
        productsListView.getItems().clear();
        productsListView.getItems().addAll(c.getProducts());

        allProductsSet.clear();
        allProductsSet.addAll(productsListView.getItems());

        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(c.getName()).append("\n")
                .append("Email: ").append(c.getEmail()).append("\n")
                .append("Phone: ").append(c.getPhone()).append("\n");
        customerDescription.setText(sb.toString());

    }

    public void listViewChooseProduct() {
        productDescription.setText("");
        StringBuilder sb = new StringBuilder();
        for (Product p : productsListView.getSelectionModel().getSelectedItems()) {
            sb.append("Title: ").append(p.getTitle()).append("\n")
                    .append("Description: ").append(p.getDescription()).append("\n")
                    .append("Product type: ").append(p.getProductType()).append("\n")
                    .append("Amortization: ").append(p.getAmortization()).append("\n")
                    .append("Scrapping criteria: ").append(p.getScrappingCriteria()).append(" years\n")
                    .append("Date registered: ").append(new Date(p.getTimestamp().getTime())).append("\n")
                    .append("\n");
        }
        productDescription.setText(sb.toString());
    }

    public void filterCustomers() {
        this.customersListView.getItems().clear();
        SearchCustomerInput input = SearchCustomerInput.builder()
                .customers(this.allCustomersSet)
                .searchWord(customerSearch.getText())
                .build();
        this.customersListView.getItems().addAll(searchCustomerOperation.process(input).getFilteredCustomers());
    }

    public void filterProducts() {
        this.productsListView.getItems().clear();
        SearchProductInput input = SearchProductInput.builder()
                .products(this.allProductsSet)
                .searchWord(productSearch.getText())
                .build();
        this.productsListView.getItems().addAll(searchProductOperation.process(input).getFilteredProducts());
    }

}
