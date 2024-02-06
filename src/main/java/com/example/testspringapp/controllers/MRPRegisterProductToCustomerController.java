package com.example.testspringapp.controllers;

import com.example.testspringapp.api.inputoutput.registerproducttocustomer.RegisterProductToCustomerInput;
import com.example.testspringapp.api.inputoutput.registerproducttocustomer.RegisterProductToCustomerOperation;
import com.example.testspringapp.api.inputoutput.searchcustomer.SearchCustomerInput;
import com.example.testspringapp.api.inputoutput.searchcustomer.SearchCustomerOperation;
import com.example.testspringapp.api.inputoutput.searchproduct.SearchProductInput;
import com.example.testspringapp.api.inputoutput.searchproduct.SearchProductOperation;
import com.example.testspringapp.configs.FxmlView;
import com.example.testspringapp.configs.StageManager;
import com.example.testspringapp.persistence.entities.Customer;
import com.example.testspringapp.persistence.entities.Product;
import com.example.testspringapp.persistence.repositories.CustomerRepository;
import com.example.testspringapp.persistence.repositories.ProductRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class MRPRegisterProductToCustomerController {

    @FXML
    private TextArea productDescription;
    @FXML
    private Button submit;
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
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final RegisterProductToCustomerOperation registerProductToCustomerOperation;
    private final SearchProductOperation searchProductOperation;
    private final SearchCustomerOperation searchCustomerOperation;

    @Autowired
    @Lazy
    public MRPRegisterProductToCustomerController(StageManager stageManager, ProductRepository productRepository, CustomerRepository customerRepository, RegisterProductToCustomerOperation registerProductToCustomerOperation, SearchProductOperation searchProductOperation, SearchCustomerOperation searchCustomerOperation) {
        this.stageManager = stageManager;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.registerProductToCustomerOperation = registerProductToCustomerOperation;
        this.searchProductOperation = searchProductOperation;
        this.searchCustomerOperation = searchCustomerOperation;
    }

    @FXML
    public void initialize() {

        allProducts.clear();
        products.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        products.getItems().addAll(productRepository.findAllByAmortizationLessThan(100d));
        allProducts.addAll(products.getItems());

        allCustomers.clear();
        customers.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        customers.getItems().addAll(customerRepository.findAll());
        allCustomers.addAll(customers.getItems());

    }

    public void registerProduct() {
        stageManager.switchScene(FxmlView.MRP_HOME_REGISTER_PRODUCT);
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

    public void viewRecords(){
        stageManager.switchScene(FxmlView.MRP_VIEW_RECORDS_CLIENTS_AND_PRODUCTS);
    }

    public void viewInbox(){
        stageManager.switchScene(FxmlView.MRP_VIEW_INBOX);
    }
    public void leave() {
        stageManager.switchScene(FxmlView.LOGIN);
    }

    public void registerProductToCustomerButtonClick() {

        RegisterProductToCustomerInput input = RegisterProductToCustomerInput.builder()
                .customers(new HashSet<>(customers.getSelectionModel().getSelectedItems()))
                .products(new HashSet<>(products.getSelectionModel().getSelectedItems()))
                .build();

        registerProductToCustomerOperation.process(input);
    }

    public void listViewChooseItem() {

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

    public void filterCustomers() {
        this.customers.getItems().clear();
        SearchCustomerInput input = SearchCustomerInput.builder()
                .customers(this.allCustomers)
                .searchWord(customerSearch.getText())
                .build();
        this.customers.getItems().addAll(searchCustomerOperation.process(input).getFilteredCustomers());
    }

    public void filterProducts() {
        this.products.getItems().clear();
        SearchProductInput input = SearchProductInput.builder()
                .products(this.allProducts)
                .searchWord(productSearch.getText())
                .build();
        this.products.getItems().addAll(searchProductOperation.process(input).getFilteredProducts());
    }

}
