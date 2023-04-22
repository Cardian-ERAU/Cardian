package com.cardian;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    private MaintenancePageController mpc = new MaintenancePageController();

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // fileIOCivic.loadData(mpc);
        // fileIOCamry.loadData(mpc);
        // fileIOVeloster.loadData(mpc);

        scene = new Scene(loadFXML("loginPage"));

        Image logo = new Image(getClass().getResourceAsStream("cardian.png"));

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Cardian");
        stage.getIcons().add(logo);
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
        launch(args);
    }
}
