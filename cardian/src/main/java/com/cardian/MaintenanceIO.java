package com.cardian;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MaintenanceIO {

    private String fileName;
    private int carNumber;
    private int mileageNumber;
    private int taskNumber;
    private int mileageNumberMax;
    private int taskNumberMax;
    private String[] mileage;
    private String[] taskName;
    private int[][] doTask;

    // Constructor
    public MaintenanceIO(String inputFile, int car, int mileageColumns, int taskRows) {
        fileName = inputFile;
        carNumber = car;
        mileageNumberMax = mileageColumns;
        taskNumberMax = taskRows;

        mileage = new String[30]; // mileage at which maintenance tasks should be performed
        taskName = new String[30]; // name of maintenance task to be performed
        doTask = new int[30][30]; // if 1, task should be performed at specified mileage

    }

    // Read car data from file and save to array
    public void loadData() {
        try {
            FileReader carReader = new FileReader(fileName);
            BufferedReader carBufferedReader = new BufferedReader(carReader);

            String line = "";

            int mileageNumber = 0;

            carBufferedReader.readLine();

            // Read mileage increments
            while (mileageNumber < mileageNumberMax) {

                line = carBufferedReader.readLine();
                mileage[mileageNumber] = line;

                mileageNumber++;
            }

            int taskNumber = 0;
            mileageNumber = 0;

            while (taskNumber < taskNumberMax) {

                // Read task names
                line = carBufferedReader.readLine();
                taskName[taskNumber] = line;

                while (mileageNumber < mileageNumberMax) {
                    // Determine if tasks should be done at specific mileage
                    line = carBufferedReader.readLine();
                    doTask[mileageNumber][taskNumber] = Integer.parseInt(line);

                    mileageNumber++;
                }

                mileageNumber = 0;
                taskNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters
    public int getMileage(int mileageNum) {
        if (mileage[mileageNum] != null) {
            String mileageString = mileage[mileageNum];
            int mileage = Integer.parseInt(mileageString);
            return mileage;
        } else {
            return 0;
        }
    }

    public String getTaskName(int taskNum) {
        if (taskName[taskNum] != null) {
            return taskName[taskNum];
        } else {
            return null;
        }
    }

    public int getDoTask(int mileageNum, int taskNum) {
        if (doTask[mileageNum][taskNum] == 1) {
            return 1;
        } else {
            return 0;
        }
    }
}