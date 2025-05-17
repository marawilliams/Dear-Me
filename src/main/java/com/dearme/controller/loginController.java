// LoginController.java
package com.dearme.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class loginController {

    @FXML
    private TextField nameField;

    @FXML
    private Button submitButton;

    @FXML
    private ImageView buttonImage;

    @FXML
    private Label errorField;

    @FXML
    private void initialize() {
        // Run after FXML loads
        nameField.setOnAction(event -> handleSubmit(event));
        Image normal = new Image(getClass().getResource("/images/star.png").toExternalForm());
        Image inverted = new Image(getClass().getResource("/images/starlight.png").toExternalForm());

        submitButton.setOnMouseEntered(e -> buttonImage.setImage(inverted));
        submitButton.setOnMouseExited(e -> buttonImage.setImage(normal));

    }

    @FXML
    private void handleSubmit(ActionEvent event) {
        String name = nameField.getText();
        if (name.trim().equals("")) {
            System.out.println("invalid name");
            errorField.setVisible(true);
            errorField.setText("Name cannot be empty");
        }
        else{
            System.out.println("Submit clicked or Enter pressed. Name = " + name);
        }

        // Additional logic (e.g. switch scene) goes here
    }

    @FXML
    private void handleExit(ActionEvent event) {
        System.exit(0);
    }
}
