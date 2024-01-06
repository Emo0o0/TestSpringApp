package com.example.testspringapp.configs;

import com.example.testspringapp.persistence.entities.User;

public class LoggedUser {

    private static User user;
    public static void setLoggedUser(User user){
        LoggedUser.user =user;
    }
    public static User getLoggedUser(){
        return user;
    }
}
