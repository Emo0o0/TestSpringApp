package com.example.testspringapp.core.processors;

import com.example.testspringapp.api.inputoutput.login.LoginOperation;
import com.example.testspringapp.api.inputoutput.login.LoginOperationInput;
import com.example.testspringapp.api.inputoutput.login.LoginOperationOutput;
import com.example.testspringapp.configs.FxmlView;
import com.example.testspringapp.configs.LoggedUser;
import com.example.testspringapp.configs.StageManager;
import com.example.testspringapp.core.exceptions.login.UserNotFoundException;
import com.example.testspringapp.core.exceptions.login.WrongPasswordException;
import com.example.testspringapp.persistence.entities.User;
import com.example.testspringapp.persistence.entities.UserType;
import com.example.testspringapp.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor (onConstructor = @__(@Lazy))
public class LoginOperationProcessor implements LoginOperation {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final StageManager stageManager;


    @Override
    public LoginOperationOutput process(LoginOperationInput input) {

        User user = userRepository.findByUsername(input.getUsername()).orElseThrow(() -> new UserNotFoundException("ACCESS DENIED"));

        if (!passwordEncoder.matches(input.getPassword(), user.getPassword()))
            throw new WrongPasswordException("ACCESS DENIED");


        System.out.println("SUCCESSFUL LOGIN");

        if (user.getUserType() == UserType.ADMINISTRATOR)
            stageManager.switchScene(FxmlView.REGISTER_MRP);
        else
            stageManager.switchScene(FxmlView.MRP_HOME_REGISTER_PRODUCT);

        LoggedUser.setLoggedUser(user);

        return LoginOperationOutput.builder()
                .username(user.getUsername())
                .success(true)
                .build();

    }
}
