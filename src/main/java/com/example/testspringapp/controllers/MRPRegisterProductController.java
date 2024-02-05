package com.example.testspringapp.controllers;

import com.example.testspringapp.api.inputoutput.registerproduct.RegisterProductInput;
import com.example.testspringapp.api.inputoutput.registerproduct.RegisterProductOperation;
import com.example.testspringapp.configs.FxmlView;
import com.example.testspringapp.configs.StageManager;
import com.example.testspringapp.core.exceptions.registerproduct.ProductInvalidAmortizationValueException;
import com.example.testspringapp.core.exceptions.registerproduct.ProductInvalidScrappingCriteriaValueException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MRPRegisterProductController {

    @FXML
    private ComboBox<String> productTypes;
    @FXML
    private TextField productTitle;
    @FXML
    private TextArea productDescription;
    @FXML
    private TextField productAmortization;
    @FXML
    private TextField scrappingCriteria;
    @FXML
    private Label invalidInformation;
    @FXML
    private Label amortizationValue;
    @FXML
    private Label scrapValue;
    @FXML
    private Button submit;
    private final StageManager stageManager;
    private final RegisterProductOperation registerProductOperation;

    @Autowired
    @Lazy
    public MRPRegisterProductController(StageManager stageManager, RegisterProductOperation registerProductOperation) {
        this.stageManager = stageManager;
        this.registerProductOperation = registerProductOperation;
    }

    @FXML
    public void initialize() {
        showProductTypes();
    }


    public void showProductTypes() {
        productTypes.getItems().add("DMA");
        productTypes.getItems().add("MA");
        productTypes.setVisibleRowCount(2);
        productTypes.getSelectionModel().selectFirst();
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
        stageManager.switchScene(FxmlView.MRP_UNREGISTER_PRODUCT_FROM_CUSTOMER);
    }

    public void viewRecords(){
        stageManager.switchScene(FxmlView.MRP_VIEW_RECORDS_CLIENTS_AND_PRODUCTS);
    }
    public void scrapProduct() {
        stageManager.switchScene(FxmlView.MRP_SCRAP_PRODUCTS);
    }

    public void leave() {
        stageManager.switchScene(FxmlView.LOGIN);
    }

    public void registerProductButtonSubmit() {

        amortizationValue.setTextFill(Color.BLACK);
        scrapValue.setTextFill(Color.BLACK);

        invalidInformation.setVisible(false);
        invalidInformation.setText("");
        invalidInformation.setTextFill(Color.RED);

        RegisterProductInput input = RegisterProductInput.builder()
                .title(productTitle.getText())
                .description(productDescription.getText())
                .productType(productTypes.getSelectionModel().getSelectedItem())
                .amortization(productAmortization.getText())
                .scrappingCriteria(scrappingCriteria.getText())
                .build();

        try {
            registerProductOperation.process(input);
            invalidInformation.setTextFill(Color.GREEN);
            invalidInformation.setText("Product "+productTitle.getText()+" registered");
        }
        catch(ProductInvalidAmortizationValueException e){
            e.printStackTrace();
            invalidInformation.setText("Invalid amortization value");
            amortizationValue.setTextFill(Color.RED);
        }
        catch(ProductInvalidScrappingCriteriaValueException e){
            e.printStackTrace();
            invalidInformation.setText("Invalid scrapping criteria");
            scrapValue.setTextFill(Color.RED);
        }
        catch (Exception e){
            e.printStackTrace();
            invalidInformation.setText("Please fill out all fields");
        }
        finally {
            invalidInformation.setVisible(true);
        }
    }

}
