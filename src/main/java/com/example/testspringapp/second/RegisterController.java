package com.example.testspringapp.second;

import com.example.testspringapp.api.inputoutput.registeruser.RegisterUserInput;
import com.example.testspringapp.api.inputoutput.registeruser.RegisterUserOperation;
import com.example.testspringapp.entities.User;
import com.example.testspringapp.repositories.UserRepository;
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

import java.util.Optional;

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

    private final RegisterUserOperation registerUserOperation;
    private final UserRepository userRepository;
    private final StageManager stageManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    public RegisterController(RegisterUserOperation registerUserOperation, UserRepository userRepository, StageManager stageManager, PasswordEncoder passwordEncoder) {
        this.registerUserOperation = registerUserOperation;
        this.userRepository = userRepository;
        this.stageManager = stageManager;
        this.passwordEncoder = passwordEncoder;
    }

    public void register() {

        success.setVisible(false);
        invalidInfo.setVisible(false);

        RegisterUserInput input = RegisterUserInput.builder()
                .username(username.getText())
                .password(password.getText())
                .confirmPassword(confirmPassword.getText())
                .build();

        try {
            registerUserOperation.process(input);
            success.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
            invalidInfo.setVisible(true);
        }
    }

    public void returnToLogin(){
        stageManager.switchScene(FxmlView.LOGIN);
    }
}
