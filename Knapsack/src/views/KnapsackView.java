package views;


import java.util.ArrayList;
import controllers.KnapsackController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pojos.Bag;
import pojos.Item;

public class KnapsackView {
	private Stage primaryStage;
	private CenterView centerView;
	private BottomView bottomView;
	private LeftView leftView;
	private RightView rightView;
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
			rightView = new RightView();
			ScrollPane sp1 = new ScrollPane();
			ScrollPane sp2 = new ScrollPane();
			sp1.setContent(centerView);
			sp2.setContent(bottomView);
			root.setLeft(leftView);
			root.setRight(rightView);
			root.setCenter(sp1);
			root.setBottom(sp2);
			Scene scene = new Scene(root,600,400);
			scene.setFill(Color.ALICEBLUE);
			primaryStage.setTitle("Multiple knapsack approximation");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	public void addItemToKnapSack(int bagIndex, Item item) {
		centerView.addItemToKnapSack(bagIndex, item);
	}
	
	public void updateKnapSacks(ArrayList<Bag> bags) {
		centerView.removeAllItemsInBags();
		for (int i = 0; i < bags.size(); i++) {
			for (int j = 0; j < bags.get(i).getnbrOfItems(); j++) {
				centerView.addItemToKnapSack(i, bags.get(i).getItems().get(j));
			}
		}
	}
	
	public void updateBottomView(ArrayList<Item> items) {
		bottomView.addAll(items, true);
	}
	
	public void updateRightView(float f) {
		rightView.setTextValue(f);
	}
	
	public void clearAllViews() {
		centerView.removeAllItemsInBags();
		bottomView.removeAllItems();
	}

}
