package com.cardian;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class LoginIO {

    // private String filename = "credentials.txt";
    private String[][] credentials;

    // public LoginIO(String filename) {
    // this.filename = filename;
    // }
    /* public static void main(String[] args) throws Exception {
        LoginIO log = new LoginIO();
        log.addCredential("bagel", "cheese");
    } */

    public boolean isValidLogin(String username, String password) throws Exception {
        boolean found = false;

        readCredentialsFile();
        System.out.println(username + " " + password);

        for (int i = 0; i < credentials.length; i++) {

            if ((username.equals(credentials[i][0])) && (password.equals(credentials[i][1]))) {
                found = true;
            }
        }

        return found;
    }

    public void addCredential(String username, String password) throws Exception {

        if (!isValidLogin(username, password)) {
            File file = new File("src/main/resources/com/cardian/credentials.txt");
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("" + (credentials.length + 1) + ",2");
            bw.newLine();

            for (int i = 0; i < credentials.length; i++) {
                bw.write(credentials[i][0] + "," + credentials[i][1]);
                bw.newLine();
            }

            bw.write(username + "," + password);

            bw.close();
            fw.close();
            
        } else {
            System.out.println("Account already in use!");
        }
    }

    public void readCredentialsFile() throws Exception {
        File file = new File("src/main/resources/com/cardian/credentials.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String row = br.readLine();
        String[] cols = row.split(",");

        int rows = Integer.parseInt(cols[0]);
        int col = Integer.parseInt(cols[1]);

        String[][] fileArray = new String[rows][col];

        for (int i = 0; i < rows; i++) {
            row = br.readLine();
            cols = row.split(",");
            fileArray[i][0] = cols[0];
            fileArray[i][1] = cols[1];
        }

        credentials = fileArray;
        br.close();
        fr.close();
    }

    public void print() {
        for (int i = 0; i < credentials.length; i++) {
            for (int j = 0; j < credentials[0].length; j++) {
                System.out.println(credentials[i][j]);
            }
        }
    }

}
