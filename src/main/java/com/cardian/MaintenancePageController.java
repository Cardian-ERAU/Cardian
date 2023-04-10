package com.cardian;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TitledPane;

public class MaintenancePageController {
    public String[][] mileage = new String[3][30]; // mileage at which maintenance tasks should be performed
    public String[][] taskName = new String[3][30]; // name of maintenance task to be performed
    public int[][][] doTask = new int[3][30][30]; // if 1, task should be performed at specified mileage

    private Stage stage;
    private Scene scene;
    private Parent root;

    private boolean loadMileageOnMouseHover = true;

    private int displayCar = 2;
    private int displayMileage = 0;
    private int displayTask = 0;

    public MaintenancePageController() {
        int car = 0;
        int mileageNumber = 0;
        int taskNumber = 0;

        // initialize mileage, task name, and do task
        while (car < 3) {
            while (mileageNumber < 30) {
                while (taskNumber < 30) {
                    mileage[car][mileageNumber] = "test";
                    // taskName[car][taskNumber] = "";
                    // doTask[car][mileageNumber][taskNumber] = 0;

                    taskNumber++;
                }

                mileageNumber++;
            }

            car++;
        }
    }

    @FXML
    public TitledPane mileage1, mileage2, mileage3;

    @FXML
    public void initialize() {
        if (loadMileageOnMouseHover) {
            mileage1.setText(mileage[displayCar][0]);
            mileage2.setText(mileage[displayCar][1]);
            mileage3.setText(mileage[displayCar][2]);
            loadMileageOnMouseHover = false;
        }
        // mileage1.setText(mileage[displayCar][0]);
        // mileage1.setText("test");
        // mileage2.setText(mileage[displayCar][1]);
        // mileage3.setText(mileage[displayCar][2]);
    }

    public void switchToMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Getters and setters
    public String getMileage(int car, int i) {
        return mileage[car][i];
    }

    public void setMileage(int car, int i, String miles) {
        mileage[car][i] = miles;

        System.out.printf("Mileage is ");
        System.out.printf(mileage[car][i]);
        System.out.printf("\n");
    }

    public String getTaskName(int car, int i) {
        return taskName[car][i];
    }

    public void setTaskName(int car, int i, String task) {
        taskName[car][i] = task;
    }

    public int getDoTask(int car, int i, int j) {
        return doTask[car][i][j];
    }

    public void setDoTask(int car, int i, int j, int trueFalse) {
        doTask[car][i][j] = trueFalse;
    }
}
