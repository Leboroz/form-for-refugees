package com.leboroz;

import com.leboroz.data.PostgresCon;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/main.fxml")));
        primaryStage.setTitle("Base de datos");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        new Thread(() -> PostgresCon.connect()).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}