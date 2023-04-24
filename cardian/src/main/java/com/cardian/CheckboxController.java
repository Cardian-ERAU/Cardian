package com.cardian;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class CheckboxController {

    @FXML
    public VBox checkboxContainer;

    @FXML
    public void initialize() {
        List<String> data = loadDataFromFile("assets\\checklist.txt"); // This sets the txt to the class data

        for (String str : data) {
            HBox hbox = new HBox(5);
            if (str.matches("^\\d.*")) {
            CheckBox checkboxright = new CheckBox(str);
            checkboxright.setSelected(false);
            hbox.getChildren().add(checkboxright);
        } else {
            hbox.getChildren().add(new Label(str));
        }

            checkboxContainer.getChildren().add(hbox);   
        }
    }

    public void switchToMainMenu() throws IOException {
        App.setRoot("mainMenu");
    }
//This reads each line from the txt file
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
