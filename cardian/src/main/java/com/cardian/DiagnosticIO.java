package com.cardian;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Stack;

public class DiagnosticIO {

    private String[][] data;
    private String[] header;
    private int count;

    public String[] displayChoices(String value) throws Exception {
        int fileNum = evaluatePosition(value);

        if (fileNum == 1) {
            readDiagnosticFile("warningLightDiagnosticData");
        } else if (fileNum == 2) {
            readDiagnosticFile("symptomDiagnosticData");
        }

        count = 0;
        return header;
    }

    public Stack displaySuggestion(String value, int direction) throws Exception {
        int choiceNum = evaluatePosition(value);
        String suggestion;
        Stack values = new Stack();
        boolean isFirstOrLast = false;

        if (direction != 0) {
            count += direction;
        } else {
            count = direction;
        }
        suggestion = data[count][choiceNum-1];
        if (suggestion.equals("null")) {
            suggestion = "Consult a Mechanic for further solutions";
            isFirstOrLast = true;
        } else if (count == 0) {
            isFirstOrLast = true;
        }

        values.push(isFirstOrLast);
        values.push(suggestion);

        return values;
    }

    private void readDiagnosticFile(String filename) throws Exception {
        File file = new File("src/main/resources/com/cardian/" + filename + ".csv");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String row = br.readLine();
        String[] cols = row.split(",");
        int numOfCols = Integer.parseInt(cols[1]);
        int numOfRows = Integer.parseInt(cols[0]);

        data = new String[numOfRows][numOfCols];

        int cnt = 0;

        row = br.readLine();
        cols = row.split(",", numOfCols);
        header = cols;

        while ((row = br.readLine()) != null) {

            cols = row.split(",", numOfCols);
            for (int i = 0; i < cols.length; i++) {

                data[cnt][i] = cols[i];
            }
            cnt++;
        }

        br.close();
        fr.close();
    }

    public void print() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                System.out.print(data[i][j] + "|");
            }
            System.out.println("");
        }
    }

    private int evaluatePosition(String string) {

        String[] first = string.split(":");

        try {
            return Integer.parseInt(first[0]);
        } catch (Exception e) {
            return 0;
        }

    }

}
