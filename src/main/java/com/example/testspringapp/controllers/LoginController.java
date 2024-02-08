package com.example.testspringapp.controllers;

import com.example.testspringapp.api.inputoutput.autoamortizationcalculate.AutoAmortizationCalculateInput;
import com.example.testspringapp.api.inputoutput.autoamortizationcalculate.AutoAmortizationCalculateOperation;
import com.example.testspringapp.api.inputoutput.autotypecalculate.AutoTypeCalculateInput;
import com.example.testspringapp.api.inputoutput.autotypecalculate.AutoTypeCalculateOperation;
import com.example.testspringapp.api.inputoutput.login.LoginOperation;
import com.example.testspringapp.api.inputoutput.login.LoginInput;
import com.example.testspringapp.persistence.repositories.UserRepository;
import com.example.testspringapp.configs.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label invalidInfo;

    private final StageManager stageManager;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final LoginOperation loginOperation;
    private final AutoTypeCalculateOperation autoTypeCalculateOperation;
    private final AutoAmortizationCalculateOperation autoAmortizationCalculateOperation;

    @Autowired
    @Lazy
    public LoginController(StageManager stageManager, UserRepository userRepository, PasswordEncoder passwordEncoder, LoginOperation loginOperation, AutoTypeCalculateOperation autoTypeCalculateOperation, AutoAmortizationCalculateOperation autoAmortizationCalculateOperation) {
        this.stageManager = stageManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginOperation = loginOperation;
        this.autoTypeCalculateOperation = autoTypeCalculateOperation;
        this.autoAmortizationCalculateOperation = autoAmortizationCalculateOperation;
    }

    public void login() {

        LoginInput input = LoginInput.builder()
                .username(username.getText())
                .password(password.getText())
                .build();

        try {
            loginOperation.process(input);
            autoAmortizationCalculateOperation.process(new AutoAmortizationCalculateInput());
            autoTypeCalculateOperation.process(new AutoTypeCalculateInput());
        } catch (Exception e) {
            invalidInfo.setVisible(true);
            e.printStackTrace();
        }
    }
}
