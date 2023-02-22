// **************************************
// Cardian
// By Zechariah Lea, Gabriel Lindo, Knox Peterson, Ashley Young
// For SE 300, Section 01DB (Prof. Towhidnejad)
// Class:  MainMenuGUI
//
// GUI for main menu.
// **************************************

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class MainMenuGUI extends Pane {
	
	//Add header and table to GUI
	public MainMenuGUI() {
		header();
	}
	
	private void header() {
		
		//Create pane label
		Label mainMenuLabel = new Label("Main Menu");
		mainMenuLabel.setFont(new Font("Arial Rounded MT Bold", 30));
		mainMenuLabel.setLayoutX(90);
		mainMenuLabel.setLayoutY(20);
		
		//Add elements to pane
		getChildren().addAll(mainMenuLabel);
	}
}