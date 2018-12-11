package controllers;

import javafx.stage.Stage;
import views.KnapsackView;

public class Knapsack {
	public Knapsack(Stage primaryStage) {
		KnapsackView knapsackView = new KnapsackView(primaryStage);
		knapsackView.initWindow();
	}

}
