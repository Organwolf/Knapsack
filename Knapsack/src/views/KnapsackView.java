package views;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pojos.Item;

public class KnapsackView {
	private Stage primaryStage;
	private CenterView centerView;
	private BottomView bottomView;
	
	public KnapsackView(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	public void initWindow() {
		try {
			BorderPane root = new BorderPane();
			centerView = new CenterView();
			bottomView = new BottomView();
			root.setCenter(centerView);
			root.setBottom(bottomView);
			Scene scene = new Scene(root,600,600);
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
	
	public void updateBottomView(Item items[]) {
		bottomView.addAll(items, true);
	}
}
