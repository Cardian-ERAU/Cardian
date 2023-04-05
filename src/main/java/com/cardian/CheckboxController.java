package com.cardian;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;

public class CheckboxController {

    @FXML
    public VBox checkboxContainer;

    @FXML
    public void initialize() {
        List<String> data = loadDataFromFile("assets\\checklist.txt"); // list.txt being the file of every thing of the
            
        for (String str : data) {
            HBox hbox = new HBox(5);
            CheckBox checkboxright = new CheckBox(str);
            CheckBox checkboxleft = new CheckBox();
            checkboxleft.setSelected(false);
            checkboxright.setSelected(false);
            hbox.getChildren().add(checkboxleft);
            hbox.getChildren().add(checkboxright);
            checkboxContainer.getChildren().add(hbox);   
        }
    }

    public void switchToMainMenu(ActionEvent event) throws IOException {
        SceneController sc = new SceneController();
        sc.switchToMainMenu(event);
    }

    private ArrayList<String> loadDataFromFile(String fileName) {
        ArrayList<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null) {
                data.add(line);
                line = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
