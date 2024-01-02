package com.example.testspringapp.core.services;

import com.example.testspringapp.api.inputoutput.registermrp.RegisterMRPInput;
import com.example.testspringapp.api.inputoutput.registermrp.RegisterMRPOperation;
import com.example.testspringapp.api.inputoutput.registermrp.RegisterMRPOutput;
import com.example.testspringapp.persistence.entities.User;
import com.example.testspringapp.persistence.entities.UserType;
import com.example.testspringapp.persistence.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterMRPOperationProcessor implements RegisterMRPOperation {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterMRPOperationProcessor(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterMRPOutput process(RegisterMRPInput input) {

        validation(input);

        User user = User.builder()
                .name(input.getUsername())
                .password(passwordEncoder.encode(input.getPassword()))
                .userType(UserType.MRP)
                .build();

        userRepository.save(user);

        return RegisterMRPOutput.builder()
                .username(input.getUsername())
                .build();
    }

    private boolean validation(RegisterMRPInput input){

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
