package com.cardian;

// **************************************
// Cardian
// By Zechariah Lea, Gabriel Lindo, Knox Peterson, Ashley Young
// For SE 300, Section 01DB (Prof. Towhidnejad)
// Class:  Manager
//
// Main class of the program, manages the entire application.
// **************************************

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Manager extends Application {

	// Set screen resolution
	public int screenWidth = 325;

	// Create menus
	private MainMenuGUI mainMenu = new MainMenuGUI();

	// Constructor
	public Manager() throws Exception {

	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		int screenHeight = screenWidth * 2;
		Scene scene = new Scene(mainMenu, screenWidth, screenHeight);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Cardian");
		primaryStage.setResizable(false);

		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void delay(int ms) {
		try {
			Thread.sleep(ms);
		}

		catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}