package com.dearme.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private static final double SCENE_WIDTH = 1250;
    private static final double SCENE_HEIGHT = 750;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Font loaded = Font.loadFont(getClass().getResource("/fonts/Ramisland.otf").toExternalForm(), 16);
        Font loaded2 = Font.loadFont(getClass().getResource("/fonts/Sand_Dunes.otf").toExternalForm(), 16);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dearme/view/log-in.fxml"));
        StackPane root = loader.load();

        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());

        // Create aura background pane
        Pane auraPane = new Pane();
        auraPane.setMouseTransparent(true);
        auraPane.getStyleClass().add("aura-pane");

        // Create one large blurred circle centered on screen
        Circle aura = new Circle(500);
        aura.setFill(Color.web("rgba(99, 107, 63,0.25)"));
        aura.setEffect(new GaussianBlur(62));
        aura.setLayoutX(SCENE_WIDTH / 2);
        aura.setLayoutY(SCENE_HEIGHT / 2);

        auraPane.getChildren().add(aura);
        root.getChildren().add(0, auraPane); // behind main UI

        primaryStage.setScene(scene);
        primaryStage.setTitle("Dear Me - Login");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
