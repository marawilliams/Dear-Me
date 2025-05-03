// Main.java
package com.dearme.app;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Random;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dearme/view/log-in.fxml"));
        Parent root = loader.load();
        StackPane stack = (StackPane) root;

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());

        // Create animated aura background
        Pane auraPane = new Pane();
        auraPane.setMouseTransparent(true); // So it doesn't block UI interaction
        auraPane.getStyleClass().add("aura-pane");

        for (int i = 0; i < 3; i++) {
            Circle aura = new Circle(200);
            aura.setFill(Color.web("rgba(255,100,200,0.15)"));
            aura.setEffect(new DropShadow(100, Color.web("#ff64c8")));
            aura.setLayoutX(600);
            aura.setLayoutY(375);
            animateAura(aura);
            auraPane.getChildren().add(aura);
        }

        stack.getChildren().add(0, auraPane); // Add aura behind main UI

        primaryStage.setScene(scene);
        primaryStage.setTitle("Dear Me - Login");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void animateAura(Circle aura) {
        Random rand = new Random();

        Timeline randomMotion = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    double offsetX = rand.nextDouble() * 400 - 200; // ±200
                    double offsetY = rand.nextDouble() * 300 - 150;

                    KeyValue kvX = new KeyValue(aura.translateXProperty(), offsetX);
                    KeyValue kvY = new KeyValue(aura.translateYProperty(), offsetY);
                    KeyFrame kf = new KeyFrame(Duration.seconds(4), kvX, kvY);

                    Timeline move = new Timeline(kf);
                    move.play();
                }),
                new KeyFrame(Duration.seconds(4))
        );

        randomMotion.setCycleCount(Animation.INDEFINITE);
        randomMotion.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
