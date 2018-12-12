package controllers;

import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.Settings;
import pojos.Item;
import views.KnapsackView;

// Controller class for the Knapsack

public class Knapsack {
	KnapsackView knapsackView;
	public Knapsack(Stage primaryStage) {
		knapsackView = new KnapsackView(primaryStage);
		knapsackView.initWindow();
		fillKnapSacksRandomly(); //Just for testing
		generateItems();		 //Just for testing
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
	
	private void generateItems() {
		Random rand = new Random();
		Item[] items = new Item[Settings.NUMBER_OF_ITEMS];
		for (int i = 0; i < Settings.NUMBER_OF_ITEMS; i++) {
			int value = rand.nextInt(5)+1;
			int weight = rand.nextInt(5)+1;
			float rValue = (float)value/weight;
			items[i] = new Item(value, weight, rValue);
		}
		knapsackView.updateBottomView(items);
	}


	public void changeText(Button btn, String str) {
		btn.setText(str); 	
	}

}
