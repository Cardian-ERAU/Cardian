package com.cardian;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class SearchController {
    
    @FXML
    public TextField searchField; // fxid
    public Text autozonePriceTag;
    public Text carpartsPriceTag;

    @FXML 
    public void initialize() {
        autozonePriceTag.setText("");
        carpartsPriceTag.setText("");
    }

    @FXML
    public void keyParser(KeyEvent key) {  
        if (!key.getCharacter().equals("\r")) {
            return;
        }  

        String searchTerm = searchField.getText(); 

        double prices[] = lookupPart(searchTerm);

        updatePriceTags(searchTerm, prices);
    }

    public void updatePriceTags(String searchTerm, double prices[]) {
        String azText = String.format("Autozone's %s is $%.2f", searchTerm, prices[0]);
        String cText = String.format("Carpart's %s is $%.2f", searchTerm, prices[1]);

        autozonePriceTag.setText(azText);
        carpartsPriceTag.setText(cText);
    }

    public double[] lookupPart(String searchTerm) {
        double prices[] = new double[2];

        prices[0] = 3.99;
        prices[1] = 2.99;

        return prices;
    }

    public void switchToMainMenu(ActionEvent event) throws IOException {
        SceneController sc = new SceneController();
        sc.switchToMainMenu(event);
    }
}
