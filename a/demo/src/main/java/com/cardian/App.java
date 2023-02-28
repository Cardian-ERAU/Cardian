package com.cardian;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    public int screenWidth = 325;
    public int aspectRatioY = 2; // height
    public int aspectRatioX = 1; // width
    public int screenHeight = (screenWidth / aspectRatioX) * aspectRatioY;

    @Override
    public void start(Stage stage) throws IOException {
        screenWidth = 325;
        scene = new Scene(loadFXML("primary"), screenWidth, screenHeight);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}