package com.cardian;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/loginPage.fxml"));
        Scene scene = new Scene(root, 600, 400);
        
        Image logo = new Image("/cardian.png");

        stage.setScene(scene);
        stage.getIcons().add(logo);
        stage.show();

    }
}
