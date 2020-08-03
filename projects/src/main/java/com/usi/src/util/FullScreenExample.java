
/*
 * Property of Urban Survival Initiative
 * Copyright (c) Notice 2020 - All rights reserved
 * 
 * This software is owned in whole by the Urban Survival Initiative.  It's intent purpose
 * is for training individuals to be software engineers.
 * 
 */

package util;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FullScreenExample extends Application {

    @Override
    public void start(Stage primaryStage) {

	Pane root = new Pane();
	Rectangle2D r = Screen.getPrimary().getBounds();
	Scene scene = new Scene(root, r.getWidth() / 2, r.getHeight() / 2);

	System.out.println("x: " + r.getWidth() + " y: " + r.getHeight());

	Rectangle rect = new Rectangle(r.getWidth() / 2, (r.getHeight() / 2) - 50);
	rect.setFill(Color.BLUE);
	rect.setStroke(Color.BLACK);
	rect.setStrokeWidth(30.5);
	Button closeButton = new Button("Close");
	Button enterButton = new Button("Enter Store");

	// action event
	EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		enterButton.setText("Welcome");
	    }
	};

	EventHandler<ActionEvent> closeEvent = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent e) {
		Stage closeStage = (Stage) closeButton.getScene().getWindow();
		closeStage.close();
	    }
	};

	enterButton.setOnAction(event);
	closeButton.setOnAction(closeEvent);

	// Set location of buttons
	closeButton.setLayoutX(100);
	closeButton.setLayoutY(100);

	enterButton.setLayoutX(500);
	enterButton.setLayoutY(100);

	root.getChildren().add(rect);
	root.getChildren().add(closeButton);
	root.getChildren().add(enterButton);

	// scene.setCursor(Cursor.NONE); // Uncomment, if you don't need a cursor
	primaryStage.setScene(scene);
	// primaryStage.setFullScreen(true);
	primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application. main()
     * serves only as fallback in case the application can not be launched through
     * deployment artifacts, e.g., in IDEs with limited FX support. NetBeans ignores
     * main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	launch(args);
    }
}
