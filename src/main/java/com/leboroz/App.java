package com.leboroz;

import com.leboroz.data.MongoCon;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
        primaryStage.show();
    }

    @Override
    public void init() {
        new Thread(MongoCon::connect);
    }


    public static void main(String[] args) {
        launch(args);
    }
}