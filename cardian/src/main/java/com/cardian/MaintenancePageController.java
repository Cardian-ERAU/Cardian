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
    public int[] mileageNumberMax = new int[3];

    public int mileageNumberMax0 = 6;
    public int mileageNumberMax1 = 11;
    public int mileageNumberMax2 = 20;

    private MaintenanceIO fileIOCivic = new MaintenanceIO("src/main/resources/com/cardian/Civic_Maintenance.txt", 0,
            mileageNumberMax0,
            15);
    private MaintenanceIO fileIOVeloster = new MaintenanceIO("src/main/resources/com/cardian/Veloster_Maintenance.txt",
            1, mileageNumberMax1, 26);
    private MaintenanceIO fileIOCamry = new MaintenanceIO("src/main/resources/com/cardian/Camry_Maintenance.txt", 2,
            mileageNumberMax2,
            9);

    public int[][] mileage = new int[3][30]; // mileage at which maintenance tasks should be performed
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
    }

    public void initializeMPC() {
        if (doInitializeMaintenancePage) {
            fileIOCivic.loadData();
            fileIOCamry.loadData();
            fileIOVeloster.loadData();

            carBox.getItems().addAll("1: 2003 Honda Civic", "2: 2016 Hyundai Veloster Turbo", "3: 2017 Toyota Camry");
            carBox.setOnAction(this::choseCar);
            doInitializeMaintenancePage = false;

            int car = 0;
            int mileageNumber = 0;
            int taskNumber = 0;

            mileageNumberMax[0] = mileageNumberMax0;
            mileageNumberMax[1] = mileageNumberMax1;
            mileageNumberMax[2] = mileageNumberMax2;

            // initialize mileage, task name, and do task
            while (car < 3) {
                while (mileageNumber < 30) {
                    while (taskNumber < 30) {
                        if (car == 0) {
                            mileage[0][mileageNumber] = fileIOCivic.getMileage(mileageNumber);
                            taskName[0][taskNumber] = fileIOCivic.getTaskName(taskNumber);
                            doTask[0][mileageNumber][taskNumber] = fileIOCivic.getDoTask(mileageNumber,
                                    taskNumber);
                        } else if (car == 1) {
                            mileage[1][mileageNumber] = fileIOVeloster.getMileage(mileageNumber);
                            taskName[1][taskNumber] = fileIOVeloster.getTaskName(taskNumber);
                            doTask[1][mileageNumber][taskNumber] = fileIOVeloster.getDoTask(mileageNumber,
                                    taskNumber);
                        } else if (car == 2) {
                            mileage[2][mileageNumber] = fileIOCamry.getMileage(mileageNumber);
                            taskName[2][taskNumber] = fileIOCamry.getTaskName(taskNumber);
                            doTask[2][mileageNumber][taskNumber] = fileIOCamry.getDoTask(mileageNumber,
                                    taskNumber);
                        }

                        taskNumber++;
                    }

                    taskNumber = 0;
                    mileageNumber++;
                }

                mileageNumber = 0;
                car++;
            }
        }
    }

    public void switchToMainMenu(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Getters and setters
    public int getMileage(int car, int i) {
        return mileage[car][i];
    }

    public void setMileage(int car, int i, int miles) {
        mileage[car][i] = miles;

        System.out.printf("Mileage is ");
        System.out.printf("%d", mileage[car][i]);
        System.out.printf("\n");
    }

    public String getTaskName(int car, int i) {
        return taskName[car][i];
    }

    public void setTaskName(int car, int i, String task) {
        taskName[car][i] = task;

        System.out.printf("Task is ");
        System.out.printf(taskName[car][i]);
        System.out.printf(", %d, %d\n", car, i);
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

    public void updateList() {
        try {
            userMileage = Integer.parseInt(mileageBox.getText());

            displayMileage = 0;

            if (userMileage >= mileage[displayCar][0]
                    && userMileage < mileage[displayCar][mileageNumberMax[displayCar] - 1]) {
                while (userMileage >= mileage[displayCar][displayMileage] && mileage[displayCar][displayMileage] != 0) {
                    int tryTask = 0;
                    int tryTaskMin = 0;
                    displayTask = 0;

                    System.out.printf("displayMileage %d\n", displayMileage);

                    while (displayTask < 12) {
                        System.out.printf("displayTask %d\n", displayTask);

                        while (tryTask < 30) {

                            if (tryTask >= tryTaskMin) {
                                System.out.printf("tryTask %d (%d)", tryTask, tryTaskMin);

                                if (doTask[displayCar][displayMileage][tryTask] == 1) {
                                    System.out.printf(", YES\n");
                                    updateListItem(displayTask, taskName[displayCar][tryTask]);

                                    tryTaskMin = tryTask + 1;
                                    tryTask = 30;
                                } else {
                                    System.out.printf("\n");
                                    updateListItem(displayTask, "");
                                }
                            }

                            tryTask++;
                        }

                        tryTask = 0;
                        displayTask++;
                    }

                    tryTaskMin = 0;
                    displayMileage++;
                }

                displayMileage = 0;
            } else if (userMileage < mileage[displayCar][0] && userMileage >= 0) {
                int tryTask = 0;
                int tryTaskMin = 0;
                displayTask = 0;

                while (displayTask < 12) {
                    System.out.printf("displayTask %d\n", displayTask);

                    while (tryTask < 30) {

                        if (tryTask >= tryTaskMin) {
                            System.out.printf("tryTask %d (%d)", tryTask, tryTaskMin);

                            if (doTask[displayCar][0][tryTask] == 1) {
                                System.out.printf(", YES\n");
                                updateListItem(displayTask, taskName[displayCar][tryTask]);

                                tryTaskMin = tryTask + 1;
                                tryTask = 30;
                            } else {
                                System.out.printf("\n");
                                updateListItem(displayTask, "");
                            }
                        }

                        tryTask++;
                    }

                    tryTask = 0;
                    displayTask++;
                }

                tryTaskMin = 0;
            } else if (userMileage >= mileage[displayCar][mileageNumberMax[displayCar] - 1]) {
                taskText1.setText("You have no upcoming");
                taskText2.setText("maintenance tasks.");
                taskText3.setText("");
                taskText4.setText("");
                taskText5.setText("Your car is very old.");
                taskText6.setText("You might consider buying");
                taskText7.setText("a new one. :)");
                taskText8.setText("");
                taskText9.setText("");
                taskText10.setText("");
                taskText11.setText("");
                taskText12.setText("");
            } else {
                invalidMileage();
            }
        } catch (NumberFormatException nfe) {
            invalidMileage();
        }
    }

    public void updateListItem(int task, String listText) {
        if (task == 0) {
            taskText1.setText(listText);
        }
        if (task == 1) {
            taskText2.setText(listText);
        }
        if (task == 2) {
            taskText3.setText(listText);
        }
        if (task == 3) {
            taskText4.setText(listText);
        }
        if (task == 4) {
            taskText5.setText(listText);
        }
        if (task == 5) {
            taskText6.setText(listText);
        }
        if (task == 6) {
            taskText7.setText(listText);
        }
        if (task == 7) {
            taskText8.setText(listText);
        }
        if (task == 8) {
            taskText9.setText(listText);
        }
        if (task == 9) {
            taskText10.setText(listText);
        }
        if (task == 10) {
            taskText11.setText(listText);
        }
        if (task == 11) {
            taskText12.setText(listText);
        }
    }

    public void invalidMileage() {
        taskText1.setText("Please input a valid mileage.");
        taskText2.setText("");
        taskText3.setText("");
        taskText4.setText("");
        taskText5.setText("");
        taskText6.setText("");
        taskText7.setText("");
        taskText8.setText("");
        taskText9.setText("");
        taskText10.setText("");
        taskText11.setText("");
        taskText12.setText("");
    }
}