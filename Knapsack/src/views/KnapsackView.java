package views;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class KnapsackView {
	private Stage primaryStage;
	
	public KnapsackView(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	public void initWindow() {
		try {
			// Creating BoarderPane root
			BorderPane root = new BorderPane();			
			// Adding buttons
			Buttons Buttons = new Buttons();
			Node VBox = Buttons.initButtons();
			root.setLeft(VBox);
			// Creating the scene
			Scene scene = new Scene(root,400,400);
			scene.setFill(Color.ALICEBLUE);
			primaryStage.setTitle("Knapsack Assignment");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
}
