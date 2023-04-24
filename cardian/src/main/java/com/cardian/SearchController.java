package com.cardian;

import java.io.IOException;

import org.htmlunit.WebClient;
import org.htmlunit.html.HtmlPage;
import org.htmlunit.html.HtmlSpan;
import javafx.scene.text.TextFlow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.scene.control.Hyperlink;


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
        String azURL = "https://www.1aauto.com/search?q=" + searchTerm.replace(' ', '+');
        Hyperlink azLink = new Hyperlink("View on 1A Auto" );
        String azText = String.format("1aauto's first listing of %s is $%.2f use this link " + azURL + " to ", searchTerm, prices[0]);
        String cText = String.format("1aauto's second listing of %s is $%.2f use this link " +azURL + " to view on 1A Auto", searchTerm, prices[1]);
//create hyperlink with URL
azLink.setOnAction(e -> {
    try {
        Desktop.getDesktop().browse(new URI(azURL));
    }catch (IOException | URISyntaxException ex) {
        ex.printStackTrace();
    }
});
// set hyperlink to text
azText += " ";
azLink.setText("view on 1A Auto");
azLink.setStyle("-fx-text-fill: blue; -fx-underline: true;");
azText += azLink.getText();
Text azTextDisplay = new Text(azText);
TextFlow azDisplay = new TextFlow(azTextDisplay, azLink);

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
