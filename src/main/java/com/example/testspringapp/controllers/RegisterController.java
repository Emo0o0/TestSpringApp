package com.example.testspringapp.controllers;

import com.example.testspringapp.api.inputoutput.registermrp.RegisterMRPInput;
import com.example.testspringapp.api.inputoutput.registermrp.RegisterMRPOperation;
import com.example.testspringapp.persistence.repositories.UserRepository;
import com.example.testspringapp.configs.FxmlView;
import com.example.testspringapp.configs.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterController {

    @FXML
    private Button register;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private Label invalidInfo;
    @FXML
    private Label success;
    @FXML
    private Label loginPage;

    private final RegisterMRPOperation registerMRPOperation;
    private final UserRepository userRepository;
    private final StageManager stageManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    public RegisterController(RegisterMRPOperation registerMRPOperation, UserRepository userRepository, StageManager stageManager, PasswordEncoder passwordEncoder) {
        this.registerMRPOperation = registerMRPOperation;
        this.userRepository = userRepository;
        this.stageManager = stageManager;
        this.passwordEncoder = passwordEncoder;
    }

    public void register() {

        success.setVisible(false);

        RegisterMRPInput input = RegisterMRPInput.builder()
                .username(username.getText())
                .password(password.getText())
                .confirmPassword(confirmPassword.getText())
                .build();

        try {
            registerMRPOperation.process(input);
            success.setTextFill(Color.LIME);
            success.setText("Successful Registration");
        }catch (Exception e){
            e.printStackTrace();
            success.setTextFill(Color.web("#f70909"));
            success.setText("Invalid Information");
        }
        success.setVisible(true);
    }

    public void returnToLogin(){
        stageManager.switchScene(FxmlView.LOGIN);
    }
}
