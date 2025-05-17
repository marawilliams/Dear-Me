// LoginController.java
package com.dearme.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class loginController {

    @FXML
    private TextField nameField;

    @FXML
    private Button submitButton;

    @FXML
    private void initialize() {
        // Run after FXML loads
        nameField.setOnAction(event -> submitButton.fire());
    }

    @FXML
    private void handleSubmit(ActionEvent event) {
        String name = nameField.getText();
        System.out.println("Submit clicked or Enter pressed. Name = " + name);

        // Additional logic (e.g. switch scene) goes here
    }

    @FXML
    private void handleExit(ActionEvent event) {
        System.exit(0);
    }
}
