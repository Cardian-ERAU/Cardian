package com.cardian;

import java.io.IOException;

import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlSpan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

public class SearchController {
    
    @FXML
    public TextField searchField; // fxid
    public Text onePriceTag;
    public Text twoPriceTag;

    @FXML 
    public void initialize() {
        onePriceTag.setText("");
        twoPriceTag.setText("");
    }

    @FXML
    public void keyParser(KeyEvent key) {  
        if (!key.getCharacter().equals("\r")) {
            return;
        }  

        String searchTerm = searchField.getText(); 

        try {
            double prices[] = lookupPart(searchTerm);
            updatePriceTags(searchTerm, prices);
        } catch (Exception e) {
            System.out.println("[!] Error looking up part data (scraper). In file SearchController.java ");
            updatePriceTags(searchTerm);
            //e.printStackTrace();

        }

    }

    public void updatePriceTags(String searchTerm, double prices[]) {
        String azText = String.format("1aauto's first listing of %s is $%.2f", searchTerm, prices[0]);
        String cText = String.format("1aauto's second listing of %s is $%.2f", searchTerm, prices[1]);

        onePriceTag.setText(azText);
        twoPriceTag.setText(cText);
    }

    /* This method only runs whenever there is an error scraping data */
    public void updatePriceTags(String searchTerm) {
        onePriceTag.setText("internal error");
        twoPriceTag.setText("internal error");
    }


    /* This method scrapes 1aauto*/
    public double[] lookupPart(String searchTerm) throws Exception {
        double prices[] = new double[2];

        final String BASE_URL = "https://www.1aauto.com/search?q=";
        searchTerm = searchTerm.replace(' ', '+');
        String requestURL  = BASE_URL + searchTerm;
        
        String firstPriceXPath = "//*[@id=\"serp-list\"]/li[1]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/span";
        String secondPriceXPath = "//*[@id=\"serp-list\"]/li[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[1]/span";

        try {
            /* Create WebClient and Set Options */
            WebClient webClient = new WebClient();
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);

            /* Request the Page */
            final HtmlPage page = webClient.getPage(requestURL);

            /* Find Elements Using XPath and Update Prices */
            HtmlSpan firstPriceSpan = (HtmlSpan) page.getByXPath(firstPriceXPath).get(0);
            prices[0] = Double.parseDouble(firstPriceSpan.getChildNodes().get(0).toString().substring(1));

            HtmlSpan secondPriceSpan = (HtmlSpan) page.getByXPath(secondPriceXPath).get(0);
            prices[1] = Double.parseDouble(secondPriceSpan.getChildNodes().get(0).toString().substring(1));

            /* Close the WebClient */
            webClient.close();
        } catch (Exception e) {
            System.out.println("[!] Error scraping web page. Error in SearchController.java");
            //e.printStackTrace();
        }

        return prices;
    }

    public void switchToMainMenu() throws IOException {
        App.setRoot("mainMenu");
    }
}
