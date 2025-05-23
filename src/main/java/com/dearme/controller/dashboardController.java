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

public class dashboardController {

    @FXML
    private Label title;

    @FXML
    private Button submitButton;

    @FXML
    private ImageView buttonImage;

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleExit(ActionEvent event) {
        System.exit(0);
    }
}
