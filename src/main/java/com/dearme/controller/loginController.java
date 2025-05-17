// LoginController.java
package com.dearme.controller;
import com.dearme.util.NameStore;


import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class loginController {

    @FXML
    private TextField nameField;

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label title;

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
        welcomeLabel.setDisable(true);
        welcomeLabel.setVisible(false);

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
            String inputName = nameField.getText();
            NameStore.setName(inputName);
            System.out.println("Submit clicked or Enter pressed. Name = " + name);
            handleFadeOut();
            //change scene to welcome "Name"
        }

        // Additional logic (e.g. switch scene) goes here
    }
    public void handleFadeOut() {
        fadeOutAndDisable(nameField, 1.5);    // fade out in 1.5 seconds
        fadeOutAndDisable(errorField, 1.5);
        fadeOutAndDisable(title, 1.5);
    }

    public void fadeOutAndDisable(Node node, double durationSeconds) {
        FadeTransition fade = new FadeTransition(Duration.seconds(durationSeconds), node);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setOnFinished(e -> node.setDisable(true)); // or node.setVisible(false)
        fade.play();
    }


    @FXML
    private void handleExit(ActionEvent event) {
        handleFadeOut();
        System.exit(0);
    }
}
