package com.example.testspringapp.controllers;

import com.example.testspringapp.api.inputoutput.searchcustomer.SearchCustomerInput;
import com.example.testspringapp.api.inputoutput.searchcustomer.SearchCustomerOperation;
import com.example.testspringapp.api.inputoutput.searchproduct.SearchProductInput;
import com.example.testspringapp.api.inputoutput.searchproduct.SearchProductOperation;
import com.example.testspringapp.api.inputoutput.unregisterproductfromcustomer.UnregisterProductFromCustomerInput;
import com.example.testspringapp.api.inputoutput.unregisterproductfromcustomer.UnregisterProductFromCustomerOperation;
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
public class MRPUnregisterProductFromCustomerController {

    @FXML
    private TextArea productDescription;
    @FXML
    private ListView<Product> products;
    private final Set<Product> allProducts = new HashSet<>();
    @FXML
    private ListView<Customer> customers;
    private final Set<Customer> allCustomers = new HashSet<>();
    @FXML
    private TextField productSearch;
    @FXML
    private TextField customerSearch;


    private final StageManager stageManager;
    private final CustomerRepository customerRepository;
    private final SearchCustomerOperation searchCustomerOperation;
    private final SearchProductOperation searchProductOperation;
    private final UnregisterProductFromCustomerOperation unregisterProductFromCustomerOperation;

    @Lazy
    public MRPUnregisterProductFromCustomerController(StageManager stageManager, CustomerRepository customerRepository, ProductRepository productRepository, SearchCustomerOperation searchCustomerOperation, SearchProductOperation searchProductOperation, UnregisterProductFromCustomerOperation unregisterProductFromCustomerOperation) {
        this.stageManager = stageManager;
        this.customerRepository = customerRepository;
        this.unregisterProductFromCustomerOperation = unregisterProductFromCustomerOperation;
        this.searchCustomerOperation = searchCustomerOperation;
        this.searchProductOperation = searchProductOperation;
    }

    @FXML
    public void initialize() {
        loadFXMLFields();
    }
    private void loadFXMLFields(){
        allCustomers.clear();
        customers.getItems().addAll(customerRepository.findAll());
        allCustomers.addAll(customers.getItems());

        products.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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

    public void scrapProduct() {
        stageManager.switchScene(FxmlView.MRP_SCRAP_PRODUCTS);
    }

    public void viewRecords(){
        stageManager.switchScene(FxmlView.MRP_VIEW_RECORDS_CLIENTS_AND_PRODUCTS);
    }

    public void viewInbox(){
        stageManager.switchScene(FxmlView.MRP_VIEW_INBOX);
    }
    public void leave() {
        stageManager.switchScene(FxmlView.LOGIN);
    }


    public void unregisterProductFromCustomerButtonClick() {

        UnregisterProductFromCustomerInput input = UnregisterProductFromCustomerInput.builder()
                .customerEmail(customers.getSelectionModel().getSelectedItem().getEmail())
                .products(new HashSet<>(products.getSelectionModel().getSelectedItems()))
                .build();

        unregisterProductFromCustomerOperation.process(input);
        listViewChooseCustomer();
        productDescription.setText("");

    }

    public void listViewChooseCustomer() {
        Customer c = customerRepository.findByEmail(customers.getSelectionModel().getSelectedItem().getEmail()).orElseThrow(() -> new CustomerInvalidEmailException("Customer not found"));
        products.getItems().clear();
        products.getItems().addAll(c.getProducts());

        allProducts.clear();
        allProducts.addAll(products.getItems());
    }

    public void listViewChooseProduct() {

        productDescription.setText("");
        StringBuilder sb = new StringBuilder();
        for (Product p : products.getSelectionModel().getSelectedItems()) {
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

    public void filterProducts() {
        this.products.getItems().clear();
        SearchProductInput input = SearchProductInput.builder()
                .products(this.allProducts)
                .searchWord(productSearch.getText())
                .build();
        this.products.getItems().addAll(searchProductOperation.process(input).getFilteredProducts());
    }

    public void filterCustomers() {
        this.customers.getItems().clear();
        SearchCustomerInput input = SearchCustomerInput.builder()
                .customers(this.allCustomers)
                .searchWord(customerSearch.getText())
                .build();
        this.customers.getItems().addAll(searchCustomerOperation.process(input).getFilteredCustomers());
    }

}
