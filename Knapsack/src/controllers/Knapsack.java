package controllers;

import java.util.Random;

import javafx.stage.Stage;
import main.Settings;
import pojos.Item;
import views.KnapsackView;

public class Knapsack {
	KnapsackView knapsackView;
	public Knapsack(Stage primaryStage) {
		knapsackView = new KnapsackView(primaryStage);
		knapsackView.initWindow();
		fillKnapSacksRandomly();
	}
	
	
	//Just for development purpose in order to test bag functionality.
	private void fillKnapSacksRandomly() {
		Random rand = new Random();
		for (int i = 0; i < Settings.NUMBER_OF_KNAPSACKS; i++) {
			for (int j = 0; j < Settings.NUMBER_OF_ITEMS; j++) {
				int value = rand.nextInt(5)+1;
				int weight = rand.nextInt(5)+1;
				float rValue = (float)value/weight;
				knapsackView.addItemToKnapSack(i, new Item(value, weight, rValue));
			}
			
		}
	}

}
