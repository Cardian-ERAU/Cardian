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
import javafx.scene.text.Text;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;

public class CheckboxController {

    @FXML
    public VBox checkboxContainer;
    //private int leftCheckboxCount = 0;  // im still working on it
   // public Text countDisplay;

    @FXML
    public void initialize() {
        List<String> data = loadDataFromFile("assets\\checklist.txt"); // This sets the txt to the class data
//This gives checkboxes to each of the lines from the txt file.
        for (String str : data) {
            HBox hbox = new HBox(5);
           // Label labelLeft = new Label("Meets Standard");
            //Label labelRight = new Label("Needs Repair");
            CheckBox checkboxright = new CheckBox(str);
           // CheckBox checkboxleft = new CheckBox();
          //  checkboxleft.setSelected(false);
            checkboxright.setSelected(false);
         /*    if (checkboxleft.isSelected()){
                leftCheckboxCount++;
            }else {
                leftCheckboxCount--;
            }*/
        //countDisplay.setText(leftCheckboxCount + "out of 160 meet the standard.");
          //  hbox.getChildren().add(checkboxleft);
            hbox.getChildren().add(checkboxright);
         //   hbox.getChildren().add(labelLeft);
         //   hbox.getChildren().add(labelRight);
            checkboxContainer.getChildren().add(hbox);   
        }
    }

    public void switchToMainMenu(ActionEvent event) throws IOException {
        SceneController sc = new SceneController();
        sc.switchToMainMenu(event);
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
