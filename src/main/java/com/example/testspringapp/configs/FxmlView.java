package com.example.testspringapp.configs;

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
    }, REGISTER_MRP {
        @Override
        String getTitle() {
            return getStringFromResourceBundle("registerUser.title");
        }

        @Override
        String getFxmlFile() {
            return "registerMRP.fxml";
        }
    };

    abstract String getTitle();

    abstract String getFxmlFile();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
}
