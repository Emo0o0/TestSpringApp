package com.example.testspringapp.core.processors;

import com.example.testspringapp.api.inputoutput.registermrp.RegisterMRPInput;
import com.example.testspringapp.api.inputoutput.registermrp.RegisterMRPOperation;
import com.example.testspringapp.api.inputoutput.registermrp.RegisterMRPOutput;
import com.example.testspringapp.core.exceptions.registermrp.BlankPasswordException;
import com.example.testspringapp.core.exceptions.registermrp.BlankUsernameException;
import com.example.testspringapp.core.exceptions.registermrp.PasswordsNotMatchingException;
import com.example.testspringapp.core.exceptions.registermrp.TakenUsernameException;
import com.example.testspringapp.persistence.entities.User;
import com.example.testspringapp.persistence.entities.UserType;
import com.example.testspringapp.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterMRPOperationProcessor implements RegisterMRPOperation {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public RegisterMRPOutput process(RegisterMRPInput input) {

        validation(input);

        User user = User.builder()
                .username(input.getUsername())
                .password(passwordEncoder.encode(input.getPassword()))
                .userType(UserType.MRP)
                .build();

        userRepository.save(user);

        return RegisterMRPOutput.builder()
                .username(input.getUsername())
                .build();
    }

    private void validation(RegisterMRPInput input){

        if(input.getUsername().isBlank()){
            throw new BlankUsernameException("Username cannot be blank");
        }
        if(input.getPassword().isBlank()){
            throw new BlankPasswordException("Password cannot be blank");
        }

        Optional<User> optionalUser = userRepository.findByUsername(input.getUsername());

        if (optionalUser.isPresent()) {
            throw new TakenUsernameException("This username is taken");
        }

        if(!input.getPassword().equals(input.getConfirmPassword())){
            throw new PasswordsNotMatchingException("Passwords do not match");
        }

    }
}
