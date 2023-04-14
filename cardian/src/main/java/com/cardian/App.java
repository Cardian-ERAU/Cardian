package com.cardian;

// **************************************
// Cardian
// By Zechariah Lea, Gabriel Lindo, Knox Peterson, Ashley Young
// For SE 300, Section 01DB (Prof. Towhidnejad)
// Class:  App
//
// Main class of the program, manages the entire application.
// **************************************

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

    public int screenWidth = 300;
    public int aspectRatioY = 2; // height
    public int aspectRatioX = 1; // width
    public int screenHeight = (screenWidth / aspectRatioX) * aspectRatioY;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        // scene = new Scene(root, screenWidth, screenHeight); //If we want to set a
        // different window size
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Cardian");
        stage.setResizable(false);
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