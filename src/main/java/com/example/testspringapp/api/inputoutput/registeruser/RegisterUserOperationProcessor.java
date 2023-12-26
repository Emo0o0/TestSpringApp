package com.example.testspringapp.api.inputoutput.registeruser;

import com.example.testspringapp.entities.User;
import com.example.testspringapp.repositories.UserRepository;
import com.example.testspringapp.second.RegisterController;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterUserOperationProcessor implements RegisterUserOperation {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserOperationProcessor(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterUserOutput process(RegisterUserInput input) {

        if(!validation(input)){
            throw new RuntimeException("Invalid data");
        }

        User user = User.builder()
                .name(input.getUsername())
                .password(passwordEncoder.encode(input.getPassword()))
                .build();

        userRepository.save(user);

        return RegisterUserOutput.builder()
                .username(input.getUsername())
                .build();
    }

    private boolean validation(RegisterUserInput input){

        if(input.getUsername().isBlank()){
            throw new RuntimeException("Username cannot be blank");
        }
        if(input.getPassword().isBlank()){
            throw new RuntimeException("Password cannot be blank");
        }

        Optional<User> optionalUser = userRepository.findByName(input.getUsername());

        if (optionalUser.isPresent()) {
            throw new RuntimeException("This username is taken");
        }

        if(!input.getPassword().equals(input.getConfirmPassword())){
            throw new RuntimeException("Passwords do not match");
        }

        return true;
    }
}
