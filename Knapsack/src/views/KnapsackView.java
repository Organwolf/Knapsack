package views;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pojos.Item;

public class KnapsackView {
	private Stage primaryStage;
	private CenterView centerView;
	
	public KnapsackView(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	public void initWindow() {
		try {
			BorderPane root = new BorderPane();
			centerView = new CenterView();
			root.setCenter(centerView);
			Scene scene = new Scene(root,400,400);
			scene.setFill(Color.ALICEBLUE);
			primaryStage.setTitle("Knapsack Assignment");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void addItemToKnapSack(int bagIndex, Item item) {
		centerView.addItemToKnapSack(bagIndex, item);
	}
}
