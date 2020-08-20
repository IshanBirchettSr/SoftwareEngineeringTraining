/**
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */
package util;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author ibirc
 *
 */
public class StoreMonitor extends Application {

    TextField txtCharacter;
    TextField txtActor;

    /**
     * 
     */
    public StoreMonitor() {

    }

    public static void main(String[] args) {
	launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
	// Create the Character label
	Label lblCharacter = new Label("Character's Name:");
	lblCharacter.setMinWidth(100);
	lblCharacter.setAlignment(Pos.BOTTOM_RIGHT);
	// Create the Character text field
	txtCharacter = new TextField();
	txtCharacter.setMinWidth(200);
	txtCharacter.setMaxWidth(200);
	txtCharacter.setPromptText("Enter the name of the character here.");
	// Create the Actor label
	Label lblActor = new Label("Actor's Name:");
	lblActor.setMinWidth(100);
	lblActor.setAlignment(Pos.BOTTOM_RIGHT);
	// Create the Actor text field
	txtActor = new TextField();
	txtActor.setMinWidth(200);
	txtActor.setMaxWidth(200);
	txtActor.setPromptText("Enter the name of the actor here.");
	// Create the Role labels
	Label lblRole1 = new Label("The role of ");
	Label lblRole2 = new Label();
	Label lblRole3 = new Label(" will be played by ");
	Label lblRole4 = new Label();
	// Create the Character pane
	HBox paneCharacter = new HBox(20, lblCharacter, txtCharacter);
	paneCharacter.setPadding(new Insets(10));
	// Create the Actor pane
	HBox paneActor = new HBox(20, lblActor, txtActor);
	paneActor.setPadding(new Insets(10));
	// Create the Role pane
	HBox paneRole = new HBox(lblRole1, lblRole2, lblRole3, lblRole4);
	paneRole.setPadding(new Insets(10));
	// Add the Character and Actor panes to a VBox
	VBox pane = new VBox(10, paneCharacter, paneActor, paneRole);
	// Create the bindings
	lblRole2.textProperty().bind(txtCharacter.textProperty());
	lblRole4.textProperty().bind(txtActor.textProperty());
	// Set the stage
	Scene scene = new Scene(pane);
	primaryStage.setScene(scene);
	primaryStage.setTitle("Role Player");
	primaryStage.show();
    }
}
