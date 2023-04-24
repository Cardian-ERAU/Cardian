package com.cardian;

import java.io.IOException;
import java.util.Arrays;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private TextField userBox, textMile, textMake, textModel;
    @FXML
    private PasswordField passBox, confirmPassBox;
    @FXML
    private ChoiceBox<String> choiceBox, problemBox;
    @FXML
    private Text errorText, errorTextTwo, problemText, hiddenText;
    @FXML
    private Button nextButton, prevButton, buttonAdd;
    @FXML
    private Label labelError;

    private LoginIO log = new LoginIO();
    private DiagnosticIO dia = new DiagnosticIO();
    private String problemChoice, make, model;
    private boolean doInitializeDiagnosticPage = true;
    private int mileage;

    public void verifyLogin() {
        try {
            if (log.isValidLogin(userBox.getText(), passBox.getText())) {

                switchToMainMenu();

            } else {
                errorText.setOpacity(0.6);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewUser() {
        if (passBox.getText().equals(confirmPassBox.getText())) {
            try {

                boolean status = log.addCredential(userBox.getText(), passBox.getText());
                if (status) {
                    switchToLogin();
                } else {
                    errorTextTwo.setOpacity(0.6);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            errorText.setOpacity(0.6);
        }

    }

    public void choseType(ActionEvent event) {
        String value = choiceBox.getValue();
        try {

            String[] choices = dia.displayChoices(value);
            problemBox.getItems().setAll(choices);
            problemBox.setOpacity(1);
            hiddenText.setOpacity(1);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void choseProblem(ActionEvent event) {
        problemChoice = problemBox.getValue();
        try {
            Stack suggestion = dia.displaySuggestion(problemChoice, 0);
            String problemSuggestion = (String) suggestion.pop();

            problemText.setOpacity(1);
            problemText.setText(problemSuggestion);
            nextButton.setOpacity(1);
            nextButton.setDisable(false);
            prevButton.setOpacity(0.6);
            prevButton.setDisable(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void changeSuggestion(ActionEvent event) throws Exception {
        Stack suggestion;
        String problemSuggestion;
        boolean isLast;
        boolean isFirst;

        if (event.getSource().equals(prevButton)) {
            suggestion = dia.displaySuggestion(problemChoice, -1);
            problemSuggestion = (String) suggestion.pop();
            isFirst = (boolean) suggestion.pop();

            if (nextButton.isDisable()) {
                nextButton.setDisable(false);
                nextButton.setOpacity(1);
            }

            if (isFirst) {
                prevButton.setDisable(true);
                prevButton.setOpacity(0.6);
            }

            problemText.setText(problemSuggestion);

        } else if (event.getSource().equals(nextButton)) {
            suggestion = dia.displaySuggestion(problemChoice, 1);
            problemSuggestion = (String) suggestion.pop();
            isLast = (boolean) suggestion.pop();

            if (prevButton.isDisable()) {
                prevButton.setDisable(false);
                prevButton.setOpacity(1);
            }

            if (isLast) {
                nextButton.setDisable(true);
                nextButton.setOpacity(0.6);
            }

            problemText.setText(problemSuggestion);
        }
    }

    public void addVehicle(ActionEvent event) {

        try {
            mileage = Integer.parseInt(textMile.getText());
            make = textMake.getText();
            model = textModel.getText();

            String[] car = { make, model };
            String[] carHC = { "Honda", "Civic" };
            String[] carTC = { "Toyota", "Camry" };
            String[] carHVT = { "Hyundai", "Veloster Turbo" };
            while (!Arrays.equals(car, carHC) && !Arrays.equals(car, carTC) && !Arrays.equals(car, carHVT)) {
                labelError.setOpacity(1);
                labelError.setText(
                        "Error: Car not supported. Please\ntry inputting 'Honda Civic',\n'Toyota Camry', or 'Hyundai\nVeloster Turbo.'");
            }

            if (Arrays.equals(car, carHC) || Arrays.equals(car, carTC) || Arrays.equals(car, carHVT)) {
                labelError.setOpacity(1);
                labelError.setText("Car accepted!");
            }
        } catch (NumberFormatException e) {
            labelError.setOpacity(1);
            labelError.setText("Error: Mileage must be an integer.");
        } catch (Exception e) {
            labelError.setOpacity(1);
            labelError.setText("Error: Please try again.");
        }
    }

    public void switchToLogin() throws IOException {
        App.setRoot("loginPage");
    }

    public void switchToNewUser() throws IOException {
        App.setRoot("newUserPage");
    }

    public void switchToMainMenu() throws IOException {
        App.setRoot("mainMenu");
    }

    public void switchToBuyingGuide() throws IOException {
        App.setRoot("buyingGuidePage");
    }

    public void switchToDiagnostic() throws IOException {
        App.setRoot("diagnosticPage");
    }

    public void initializeChoiceBoxes() {
        if (doInitializeDiagnosticPage) {
            choiceBox.getItems().addAll("1: Warning Light", "2: Symptom");
            choiceBox.setOnAction(this::choseType);
            problemBox.setOnAction(this::choseProblem);
            doInitializeDiagnosticPage = false;
        }
    }

    public void switchToMaintenance() throws IOException {
        App.setRoot("maintenancePage");
    }

    public void switchToPartsLookup() throws IOException {
        App.setRoot("partsLookupPage");

    }

    public void switchToAddVehicle() throws IOException {
        App.setRoot("addVehiclePage");
    }
}
