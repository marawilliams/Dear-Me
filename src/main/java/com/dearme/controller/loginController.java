// LoginController.java
package com.dearme.controller;

import com.dearme.util.NameStore;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
        String name = NameStore.getName();
        if(name == null){
            nameField.setOnAction(event -> handleSubmit(event));
            Image normal = new Image(getClass().getResource("/images/star.png").toExternalForm());
            Image inverted = new Image(getClass().getResource("/images/starlight.png").toExternalForm());

            submitButton.setOnMouseEntered(e -> buttonImage.setImage(inverted));
            submitButton.setOnMouseExited(e -> buttonImage.setImage(normal));

            welcomeLabel.setDisable(true);
            welcomeLabel.setVisible(false);
            welcomeLabel.setOpacity(0);
        }
        else{
            generateGreeting(name);
            welcomeLabel.setDisable(false);
            welcomeLabel.setVisible(true);
            welcomeLabel.setOpacity(1);
            Image normal = new Image(getClass().getResource("/images/star.png").toExternalForm());
            Image inverted = new Image(getClass().getResource("/images/starlight.png").toExternalForm());

            submitButton.setOnMouseEntered(e -> buttonImage.setImage(inverted));
            submitButton.setOnMouseExited(e -> buttonImage.setImage(normal));

        }

    }


    @FXML
    private void handleSubmit(ActionEvent event) {
        String name = nameField.getText().toLowerCase();
        if (name.trim().equals("")) {
            System.out.println("invalid name");
            errorField.setVisible(true);
            errorField.setText("Name cannot be empty");
        } else {
            NameStore.setName(name);
            System.out.println("Submit clicked or Enter pressed. Name = " + name);
            handleFadeOut(name);
        }
    }

    private static final List<String> GREETINGS = Arrays.asList(
            "hi hi", "what's up", "yoooooooo", "hope you're vibing today", "you look GORGEOUS", "you kewl"
    );

    public static String getRandomGreeting(String name) {
        String greeting = GREETINGS.get(new Random().nextInt(GREETINGS.size()));
        return greeting + ", " + name + "!";
    }


    private void generateGreeting(String name) {
        Font customFont = Font.loadFont(getClass().getResource("/fonts/always_forever.ttf").toExternalForm(), 24);
        String greeting = getRandomGreeting(name);
        welcomeLabel.setFont(Font.font(customFont.getFamily(), 60));
        welcomeLabel.setText(greeting);
        welcomeLabel.setDisable(false);
        welcomeLabel.setVisible(true);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.0), welcomeLabel);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    public void handleFadeOut(String name) {
        FadeTransition fade1 = createFadeTransition(nameField, 1.5);
        FadeTransition fade2 = createFadeTransition(errorField, 1.5);
        FadeTransition fade3 = createFadeTransition(title, 1.5);

        // After the last element fades out, fade in the welcome label
        fade3.setOnFinished(e -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5)); // Pause duration
            pause.setOnFinished(event -> generateGreeting(name));
            pause.play();
        });
        fade1.play();
        fade2.play();
        fade3.play();
    }

    private FadeTransition createFadeTransition(Node node, double durationSeconds) {
        FadeTransition fade = new FadeTransition(Duration.seconds(durationSeconds), node);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);
        fade.setOnFinished(event -> node.setDisable(true));
        return fade;
    }

    @FXML
    private void handleExit(ActionEvent event) {
        handleFadeOut("Guest");
        System.exit(0);
    }
}
