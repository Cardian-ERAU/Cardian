// **************************************
// Cardian
// By Zechariah Lea, Gabriel Lindo, Knox Peterson, Ashley Young
// For SE 300, Section 01DB (Prof. Towhidnejad)
// Class:  MainMenuGUI
//
// GUI for main menu.
// **************************************

import javafx.scene.control.Label;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class DiagnosisPageGUI extends Pane {
	
	//Add header and table to GUI
	public DiagnosisPageGUI() {
		header();
	}
	
	private void header() {
		
		//Create pane label
		Label diagnosisLabel = new Label("Diagnosis Page");
		diagnosisLabel.setFont(new Font("Arial Rounded MT Bold", 30));
		diagnosisLabel.setLayoutX(90);
		diagnosisLabel.setLayoutY(20);
		
		//Create location choicebox
		ChoiceBox<String> locationBox = new ChoiceBox<String>();
		locationBox.getItems().addAll("Engine Bay", "Trunk", "Cabin");
		locationBox.setLayoutX(90);
		locationBox.setLayoutY(100);
		
		//Add elements to pane
		getChildren().addAll(diagnosisLabel, locationBox);
	}
}