package views;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class KnapsackView {
	private Stage primaryStage;
	
	public KnapsackView(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	public void initWindow() {
		try {
			BorderPane root = new BorderPane();
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
