package com.cardian;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class MaintenancePageController {
    public String[][] mileage = new String[3][30]; // mileage at which maintenance tasks should be performed
    public String[][] taskName = new String[3][30]; // name of maintenance task to be performed
    public int[][][] doTask = new int[3][30][30]; // if 1, task should be performed at specified mileage

    private Stage stage;
    private Scene scene;
    private Parent root;

    private boolean doInitializeMaintenancePage = true;

    private int displayCar = 0;
    private int displayMileage = 0;
    private int displayTask = 0;

    int userMileage = 0;

    @FXML
    private TextField mileageBox;
    @FXML
    private ChoiceBox<String> carBox;
    @FXML
    private Text taskText1, taskText2, taskText3, taskText4, taskText5, taskText6, taskText7, taskText8, taskText9,
            taskText10, taskText11, taskText12;

    public MaintenancePageController() {
        int car = 0;
        int mileageNumber = 0;
        int taskNumber = 0;

        // initialize mileage, task name, and do task
        while (car < 3) {
            while (mileageNumber < 30) {
                while (taskNumber < 30) {
                    mileage[car][mileageNumber] = "";
                    taskName[car][taskNumber] = "";
                    doTask[car][mileageNumber][taskNumber] = 0;

                    taskNumber++;
                }

                mileageNumber++;
            }

            car++;
        }
    }

    @FXML
    public void initialize() {

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

        // System.out.printf("Mileage is ");
        // System.out.printf(mileage[car][i]);
        // System.out.printf("\n");
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

    public void choseCar(ActionEvent event) {
        String value = carBox.getValue();
        try {
            setCar(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCar(String value) throws Exception {
        int fileNum = evaluatePosition(value);

        displayCar = fileNum - 1;
    }

    private int evaluatePosition(String string) {

        String[] first = string.split(":");

        try {
            return Integer.parseInt(first[0]);
        } catch (Exception e) {
            return 0;
        }

    }

    public void initializeCarBox() {
        if (doInitializeMaintenancePage) {
            carBox.getItems().addAll("1: 2003 Honda Civic", "2: 2016 Hyundai Veloster Turbo", "3: 2017 Toyota Camry");
            carBox.setOnAction(this::choseCar);
            doInitializeMaintenancePage = false;
        }
    }

    public void inputMileage() {
        userMileage = Integer.parseInt(mileageBox.getText());

        int tryMileage = 0;

        while (userMileage < Integer.parseInt(mileage[displayCar][tryMileage])) {
            int setTask = 0;
            int tryTask = 0;

            while (setTask < 12) {
                while (tryTask < 30) {
                    if (doTask[displayCar][tryMileage - 1][tryTask] == 1) {
                        taskText1.setText(taskName[displayCar][tryTask]);
                    }

                    setTask++;
                }

                setTask++;
            }

            tryMileage++;
        }
    }
}