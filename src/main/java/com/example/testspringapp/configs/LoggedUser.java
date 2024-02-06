package com.example.testspringapp.configs;

import com.example.testspringapp.core.exceptions.login.UserNotFoundException;
import com.example.testspringapp.persistence.entities.User;
import com.example.testspringapp.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoggedUser {

    @Autowired
    private static UserRepository userRepository;
    private static User user;


    public static void setLoggedUser(User user){
        LoggedUser.user =user;
    }
    public static User getLoggedUser(){
        return user;
        //return userRepository.findByUsername(user.getUsername()).orElseThrow(()->new UserNotFoundException("User not found"));
    }
}
