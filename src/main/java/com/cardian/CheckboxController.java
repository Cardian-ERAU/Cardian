package com.cardian;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;

public class CheckboxController {

    @FXML
    public VBox checkboxContainer;

    @FXML
    public void initialize() {
        List<String> data = loadDataFromFile("assets\\checklist.txt"); // list.txt being the file of every thing of the
                                                                       // checkbox
        ScrollPane scrollPane = new ScrollPane(); // create a scrollpane
        scrollPane.setContent(checkboxContainer); // set content of scrollpane
        scrollPane.setPrefSize(400, 400); // sizing of the scrollpane

        for (String str : data) {
            CheckBox checkboxx = new CheckBox(str);
            checkboxx.setSelected(false);
            checkboxContainer.getChildren().add(checkboxx);
        }
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
