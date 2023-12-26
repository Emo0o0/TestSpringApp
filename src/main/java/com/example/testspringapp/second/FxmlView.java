package com.example.testspringapp.second;

import java.util.ResourceBundle;

public enum FxmlView {

     LOGIN {
        @Override
        String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
        String getFxmlFile() {
            return "login.fxml";
        }
    }, REGISTER{
        @Override
        String getTitle() {
            return getStringFromResourceBundle("register.title");
        }

        @Override
        String getFxmlFile() {
            return "register.fxml";
        }
    };

    abstract String getTitle();

    abstract String getFxmlFile();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
}
