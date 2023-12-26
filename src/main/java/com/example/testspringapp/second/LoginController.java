package com.example.testspringapp.second;

import com.example.testspringapp.entities.User;
import com.example.testspringapp.repositories.UserRepository;
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

    @Autowired
    @Lazy
    public LoginController(StageManager stageManager, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.stageManager = stageManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void login() {

        Optional<User> optionalUser = userRepository.findByName(username.getText());

        if(optionalUser.isEmpty()) {
            invalidInfo.setVisible(true);
            throw new RuntimeException("Access denied");
        }

        if(!passwordEncoder.matches(password.getText(),optionalUser.get().getPassword())){
            invalidInfo.setVisible(true);
            throw new RuntimeException("Access denied");
        }

        System.out.println("SUCCESSFUL LOGIN");

    }

    public void register(){
        stageManager.switchScene(FxmlView.REGISTER);
    }

}
