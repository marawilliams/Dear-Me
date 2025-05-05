package com.dearme.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load custom fonts
        Font.loadFont(getClass().getResource("/fonts/Ramisland.otf").toExternalForm(), 16);
        Font.loadFont(getClass().getResource("/fonts/Sand_Dunes.otf").toExternalForm(), 16);

        // Load FXML and apply stylesheet
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dearme/view/log-in.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());

        // Set up the stage
        primaryStage.setTitle("Dear Me - Login");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
