package com.cardian;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileIO {

    private String fileName;
    private int carNumber;
    private int mileageNumber;
    private int taskNumber;
    private int mileageNumberMax;
    private int taskNumberMax;
    private int[] mileage;
    private String[] taskName;
    private int[][] doTask;
    // mileage

    public FileIO(String inputFile, int car, int mileageColumns, int taskRows) {
        fileName = inputFile;
        carNumber = car - 1;
        mileageNumberMax = mileageColumns;
        taskNumberMax = taskRows;

        mileage = new int[mileageNumberMax]; // mileage at which maintenance tasks should be performed
        taskName = new String[taskNumberMax]; // name of maintenance task to be performed
        doTask = new int[mileageNumberMax][taskNumberMax]; // if 1, task should be performed at specified
                                                           // mileage

    }

    // private File carFile = new File(fileName);

    public void loadData(MaintenancePageController mpc) {

        try {

            FileReader carReader = new FileReader(fileName);
            BufferedReader carBufferedReader = new BufferedReader(carReader);

            String line = "";

            int mileageNumber = 0;

            carBufferedReader.readLine();

            // Read mileage increments
            while (mileageNumber < mileageNumberMax) {

                line = carBufferedReader.readLine();
                // System.out.println(line);
                mileage[mileageNumber] = Integer.parseInt(line);
                mpc.setMileage(carNumber, mileageNumber, mileage[mileageNumber]);

                mileageNumber++;
            }

            int taskNumber = 0;
            mileageNumber = 0;

            while (taskNumber < taskNumberMax) {

                // Read task names
                line = carBufferedReader.readLine();
                taskName[taskNumber] = line;
                mpc.setTaskName(carNumber, taskNumber, taskName[taskNumber]);

                while (mileageNumber < mileageNumberMax) {
                    // Determine if tasks should be done at specific mileage
                    line = carBufferedReader.readLine();
                    doTask[mileageNumber][taskNumber] = Integer.parseInt(line);
                    mpc.setDoTask(carNumber, mileageNumber, taskNumber, doTask[mileageNumber][taskNumber]);

                    mileageNumber++;
                }

                taskNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}