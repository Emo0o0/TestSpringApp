package com.example.testspringapp.controllers;

import com.example.testspringapp.api.inputoutput.registerproducttocustomer.searchproduct.SearchProductInput;
import com.example.testspringapp.api.inputoutput.registerproducttocustomer.searchproduct.SearchProductOperation;
import com.example.testspringapp.api.inputoutput.scrapproduct.ScrapProductsInput;
import com.example.testspringapp.api.inputoutput.scrapproduct.ScrapProductsOperation;
import com.example.testspringapp.configs.FxmlView;
import com.example.testspringapp.configs.StageManager;
import com.example.testspringapp.core.exceptions.scrapproduct.ProductInCustomerCardException;
import com.example.testspringapp.persistence.entities.Product;
import com.example.testspringapp.persistence.repositories.ProductRepository;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MRPScrapProductsController {

    @FXML
    private TextField productSearch;
    @FXML
    private ListView<Product> products;
    private Set<Product> allProducts = new HashSet<>();
    @FXML
    private TextArea productDescription;
    @FXML
    private Label errorMessage;
    private final StageManager stageManager;
    private final ProductRepository productRepository;
    private final SearchProductOperation searchProductOperation;
    private final ScrapProductsOperation scrapProductsOperation;

    @Lazy
    public MRPScrapProductsController(StageManager stageManager, ProductRepository productRepository, SearchProductOperation searchProductOperation, ScrapProductsOperation scrapProductsOperation) {
        this.stageManager = stageManager;
        this.productRepository = productRepository;
        this.searchProductOperation = searchProductOperation;
        this.scrapProductsOperation = scrapProductsOperation;
    }

    @FXML
    public void initialize(){
        products.getItems().clear();
        products.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        products.getItems().addAll(productRepository.findAllByAmortizationLessThan(100d));

        allProducts.clear();
        allProducts.addAll(products.getItems());
    }

    public void registerProduct() {
        stageManager.switchScene(FxmlView.MRP_HOME_REGISTER_PRODUCT);
    }


    public void addProductToClientCard() {
        stageManager.switchScene(FxmlView.MRP_REGISTER_PRODUCT_TO_CUSTOMER);
    }

    public void removeProductFromClientCard() {
        stageManager.switchScene(FxmlView.MRP_UNREGISTER_PRODUCT_FROM_CUSTOMER);
    }
    public void registerCustomer() {
        stageManager.switchScene(FxmlView.MRP_HOME_REGISTER_CUSTOMER);
    }

    public void viewRecords(){
        stageManager.switchScene(FxmlView.MRP_VIEW_RECORDS_CLIENTS_AND_PRODUCTS);
    }

    public void leave() {
        stageManager.switchScene(FxmlView.LOGIN);
    }

    public void ScrapProductsButtonSubmit(){
        errorMessage.setVisible(false);
        ScrapProductsInput input=ScrapProductsInput.builder()
                .productsToScrap(new HashSet<>(products.getSelectionModel().getSelectedItems()))
                .build();
        try{
            scrapProductsOperation.process(input);
            products.getItems().clear();
            products.getItems().addAll(productRepository.findAllByAmortizationLessThan(100d));
        }catch (ProductInCustomerCardException e){
            e.printStackTrace();
            errorMessage.setVisible(true);
        }
    }

    public void filterProducts() {
        this.products.getItems().clear();
        SearchProductInput input = SearchProductInput.builder()
                .products(this.allProducts)
                .searchWord(productSearch.getText())
                .build();
        this.products.getItems().addAll(searchProductOperation.process(input).getFilteredProducts());
    }

    public void listViewChooseProduct() {

        productDescription.setText("");
        StringBuilder sb = new StringBuilder();
        for (Product p : products.getSelectionModel().getSelectedItems()) {
            sb.append(p.getTitle()).append("\n")
                    .append(p.getDescription()).append("\n")
                    .append(p.getProductType()).append("\n")
                    .append(p.getAmortization()).append("\n")
                    .append(p.getScrappingCriteria()).append("\n")
                    .append("\n");
        }
        productDescription.setText(sb.toString());
    }
}
