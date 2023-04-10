package com.cardian;

public class MaintenancePageController {
    public int[][] mileage = new int[3][30]; // mileage at which maintenance tasks should be performed
    public String[][] taskName = new String[3][30]; // name of maintenance task to be performed
    public int[][][] doTask = new int[3][30][30]; // if 1, task should be performed at specified mileage

    public MaintenancePageController() {
        int car = 0;
        int mileageNumber = 0;
        int taskNumber = 0;

        // initialize mileage, task name, and do task
        while (car < 3) {
            while (mileageNumber < 30) {
                while (taskNumber < 30) {
                    mileage[car][mileageNumber] = 0;
                    taskName[car][taskNumber] = "";
                    doTask[car][mileageNumber][taskNumber] = 0;

                    taskNumber++;
                }

                mileageNumber++;
            }

            car++;
        }
    }

    // Getters and setters
    public int getMileage(int car, int i) {
        return mileage[car][i];
    }

    public void setMileage(int car, int i, int miles) {
        mileage[car][i] = miles;
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
