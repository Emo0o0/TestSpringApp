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
    }, MRP_HOME{
        @Override
        String getTitle() {
            return getStringFromResourceBundle("MRPHome.title");
        }

        @Override
        String getFxmlFile() {
            return "MRPHome.fxml";
        }
    }, MRP_HOME_REGISTER_PRODUCT {
        @Override
        String getTitle() {
            return getStringFromResourceBundle("MRPHomeRegisterProduct.title");
        }

        @Override
        String getFxmlFile() {
            return "MRPRegisterProduct.fxml";
        }
    }, MRP_HOME_REGISTER_CUSTOMER{
        @Override
        String getTitle() {
            return getStringFromResourceBundle("MRPHomeRegisterCustomer.title");
        }

        @Override
        String getFxmlFile() {
            return "MRPRegisterCustomer.fxml";
        }
    }, MRP_REGISTER_PRODUCT_TO_CUSTOMER {
        @Override
        String getTitle() {
            return getStringFromResourceBundle("MRPRegisterProductToCustomer.title");
        }

        @Override
        String getFxmlFile() {
            return "MRPRegisterProductToCustomer.fxml";
        }
    };

    abstract String getTitle();

    abstract String getFxmlFile();

    String getStringFromResourceBundle(String key) {
        return ResourceBundle.getBundle("Bundle").getString(key);
    }
}
