package com.example.testspringapp.controllers;

import com.example.testspringapp.api.inputoutput.searchproduct.SearchProductInput;
import com.example.testspringapp.api.inputoutput.searchproduct.SearchProductOperation;
import com.example.testspringapp.configs.FxmlView;
import com.example.testspringapp.configs.StageManager;
import com.example.testspringapp.persistence.entities.Product;
import com.example.testspringapp.persistence.entities.ProductType;
import com.example.testspringapp.persistence.repositories.ProductRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class MRPRecordsAllProductsController {

    @FXML
    private Label clientsAndProducts;
    @FXML
    private Label allProducts;
    @FXML
    private ComboBox<String> productsComboBox;
    @FXML
    private ListView<Product> productListView;
    @FXML
    private TextField productSearch;
    @FXML
    private TextArea productDescription;
    private final Set<Product> allProductsSet=new HashSet<>();

    private final StageManager stageManager;
    private final ProductRepository productRepository;
    private final SearchProductOperation searchProductOperation;
    @Lazy
    public MRPRecordsAllProductsController(StageManager stageManager, ProductRepository productRepository, SearchProductOperation searchProductOperation) {
        this.stageManager = stageManager;
        this.productRepository = productRepository;
        this.searchProductOperation = searchProductOperation;
    }

    @FXML
    public void initialize(){
        loadFXMLFields();
    }

    private void loadFXMLFields(){
        productsComboBox.getItems().add("All products");
        productsComboBox.getItems().add("DMA");
        productsComboBox.getItems().add("MA");
        productsComboBox.getItems().add("Scrapped products");
        productsComboBox.setVisibleRowCount(4);
        productsComboBox.getSelectionModel().selectFirst();
        comboBoxIndexChanged();

        productListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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

    public void showClientsAndProducts(){
        stageManager.switchScene(FxmlView.MRP_VIEW_RECORDS_CLIENTS_AND_PRODUCTS);
    }

    public void comboBoxIndexChanged(){
        if(productsComboBox.getSelectionModel().getSelectedIndex()==0){
            productListView.getItems().clear();
            productListView.getItems().addAll(productRepository.findAll());
            allProductsSet.clear();
            allProductsSet.addAll(productListView.getItems());
        }
        if(productsComboBox.getSelectionModel().getSelectedIndex()==1){
            productListView.getItems().clear();
            productListView.getItems().addAll(productRepository.findAllByProductType(ProductType.DMA));
            allProductsSet.clear();
            allProductsSet.addAll(productListView.getItems());
        }
        if(productsComboBox.getSelectionModel().getSelectedIndex()==2){
            productListView.getItems().clear();
            productListView.getItems().addAll(productRepository.findAllByProductType(ProductType.MA));
            allProductsSet.clear();
            allProductsSet.addAll(productListView.getItems());
        }
        if(productsComboBox.getSelectionModel().getSelectedIndex()==3){
            productListView.getItems().clear();
            productListView.getItems().addAll(productRepository.findByAmortization(100d));
            allProductsSet.clear();
            allProductsSet.addAll(productListView.getItems());
        }
    }
    public void filterProducts(){
        this.productListView.getItems().clear();
        SearchProductInput input = SearchProductInput.builder()
                .products(this.allProductsSet)
                .searchWord(productSearch.getText())
                .build();
        this.productListView.getItems().addAll(searchProductOperation.process(input).getFilteredProducts());
    }

    public void listViewChooseProduct() {

        productDescription.setText("");
        StringBuilder sb = new StringBuilder();
        for (Product p : productListView.getSelectionModel().getSelectedItems()) {
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
}
