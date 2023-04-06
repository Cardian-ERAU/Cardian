package com.cardian;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private TextField userBox;
    @FXML
    private PasswordField passBox;
    @FXML
    private Text errorText;
    @FXML
    private PasswordField confirmPassBox;

    private LoginIO log = new LoginIO();

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
                log.addCredential(userBox.getText(), passBox.getText());
                switchToLogin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            errorText.setOpacity(0.6);
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

    public void switchToMaintenance() throws IOException {
        App.setRoot("maintenancePage");
    }

    public void switchToPartsLookup() throws IOException {
        App.setRoot("partsLookupPage");
    }
}
