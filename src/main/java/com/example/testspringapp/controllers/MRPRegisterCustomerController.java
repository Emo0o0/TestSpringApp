package com.example.testspringapp.controllers;

import com.example.testspringapp.api.inputoutput.registercustomer.RegisterCustomerInput;
import com.example.testspringapp.api.inputoutput.registercustomer.RegisterCustomerOperation;
import com.example.testspringapp.configs.FxmlView;
import com.example.testspringapp.configs.StageManager;
import com.example.testspringapp.core.exceptions.registercustomer.CustomerAlreadyExistsException;
import com.example.testspringapp.core.exceptions.registercustomer.CustomerInvalidEmailException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MRPRegisterCustomerController {

//    @FXML
//    private Label registerProductLabel;
//    @FXML
//    private Label scrappingCriteriaLabel;
//    @FXML
//    private Label registerCustomerLabel;
//    @FXML
//    private Label addProductsLabel;
//    @FXML
//    private Label removeProductsLabel;
//    @FXML
//    private Label scrapProductLabel;
//    @FXML
//    private Label leaveLabel;
    @FXML
    private TextField clientName;
    @FXML
    private TextField clientEmail;
    @FXML
    private TextField clientPhone;
    @FXML
    private Button submit;
    @FXML
    private Label formFillError;
    private final StageManager stageManager;
    private final RegisterCustomerOperation registerCustomerOperation;

    @Autowired
    @Lazy
    public MRPRegisterCustomerController(StageManager stageManager, RegisterCustomerOperation registerCustomerOperation) {
        this.stageManager = stageManager;
        this.registerCustomerOperation = registerCustomerOperation;
    }

    @FXML
    public void initialize() {
    }



    public void registerCustomer() {

        formFillError.setVisible(false);
        formFillError.setText("");

        RegisterCustomerInput input = RegisterCustomerInput.builder()
                .name(clientName.getText())
                .phone(clientPhone.getText())
                .email(clientEmail.getText())
                .build();
        try{
            registerCustomerOperation.process(input);
        }catch (CustomerAlreadyExistsException e){
            e.printStackTrace();
            formFillError.setText("This username is already taken");
        }catch(CustomerInvalidEmailException e){
            e.printStackTrace();
            formFillError.setText("Please enter a valid email");
        }
        catch(Exception e){
            e.printStackTrace();
            formFillError.setText("Please fill out the form");
        }
        finally {
            formFillError.setVisible(true);
        }
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

    public void scrapProduct() {
        stageManager.switchScene(FxmlView.MRP_SCRAP_PRODUCTS);
    }

    public void viewRecords(){
        stageManager.switchScene(FxmlView.MRP_VIEW_RECORDS_CLIENTS_AND_PRODUCTS);
    }

    public void leave() {
        stageManager.switchScene(FxmlView.LOGIN);
    }

}
