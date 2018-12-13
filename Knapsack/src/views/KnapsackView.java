package views;

import java.util.ArrayList;

import controllers.KnapsackController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pojos.Bag;
import pojos.Item;

public class KnapsackView {
	private Stage primaryStage;
	private CenterView centerView;
	private BottomView bottomView;
	private LeftView leftView;
	// is this what I should instantiate to be able to send
	// the controller to the LeftView?
	private KnapsackController controller;
	
	public KnapsackView(Stage primaryStage, KnapsackController controller) {
		this.primaryStage = primaryStage;
		this.controller = controller;
	}
	public void initWindow() {
		try {
			BorderPane root = new BorderPane();
			leftView = new LeftView(controller);
			centerView = new CenterView();
			bottomView = new BottomView();
			ScrollPane sp1 = new ScrollPane();
			ScrollPane sp2 = new ScrollPane();
			sp1.setContent(centerView);
			sp2.setContent(bottomView);
			root.setLeft(leftView);				// Left view for buttons
			root.setCenter(sp1);
			root.setBottom(sp2);
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
	
	public void updateBottomView(ArrayList<Item> items) {
		bottomView.addAll(items, true);
	}

}
