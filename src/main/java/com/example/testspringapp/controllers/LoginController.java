package com.example.testspringapp.controllers;

import com.example.testspringapp.api.inputoutput.login.LoginOperation;
import com.example.testspringapp.api.inputoutput.login.LoginOperationInput;
import com.example.testspringapp.persistence.entities.User;
import com.example.testspringapp.persistence.entities.UserType;
import com.example.testspringapp.persistence.repositories.UserRepository;
import com.example.testspringapp.configs.FxmlView;
import com.example.testspringapp.configs.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button login;
    @FXML
    private Button register;
    @FXML
    private Label invalidInfo;

    private final StageManager stageManager;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final LoginOperation loginOperation;

    @Autowired
    @Lazy
    public LoginController(StageManager stageManager, UserRepository userRepository, PasswordEncoder passwordEncoder, LoginOperation loginOperation) {
        this.stageManager = stageManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginOperation = loginOperation;
    }

    public void login() {

        LoginOperationInput input = LoginOperationInput.builder()
                .username(username.getText())
                .password(password.getText())
                .build();

        try {
            loginOperation.process(input);
        }catch (Exception e){
            invalidInfo.setVisible(true);
            e.printStackTrace();
        }
    }
}
