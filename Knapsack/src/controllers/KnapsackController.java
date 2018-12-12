package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.Settings;
import pojos.Bag;
import pojos.Item;
import views.KnapsackView;

// Controller class for the Knapsack

public class KnapsackController {
	private KnapsackView knapsackView;
	private Bag[] bags;
	private ArrayList<Item> availableItems;
	public KnapsackController(Stage primaryStage) {
		knapsackView = new KnapsackView(primaryStage);
		knapsackView.initWindow();
		//fillKnapSacksRandomly(); //Just for testing
		generateItems();		 //Just for testing
		generateGreedySolution();
		bags = new Bag[Settings.NUMBER_OF_KNAPSACKS];
	}
	
	
	//Just for development purpose in order to test bag functionality.
//	private void fillKnapSacksRandomly() {
//		Random rand = new Random();
//		for (int i = 0; i < Settings.NUMBER_OF_KNAPSACKS; i++) {
//			for (int j = 0; j < Settings.NUMBER_OF_ITEMS; j++) {
//				int value = rand.nextInt(5)+1;
//				int weight = rand.nextInt(5)+1;
//				float rValue = (float)value/weight;
//				knapsackView.addItemToKnapSack(i, new Item(value, weight, rValue));
//			}
//		}
//	}
	
	private void generateItems() {
		Random rand = new Random();
		availableItems = new ArrayList<>();
		for (int i = 0; i < Settings.NUMBER_OF_ITEMS; i++) {
			int value = rand.nextInt(5)+1;
			int weight = rand.nextInt(5)+1;
			float rValue = (float)value/weight;
			availableItems.add(new Item(value, weight, rValue));
		}
		knapsackView.updateBottomView(availableItems);
	}
	
	public void generateGreedySolution() {
		for (int i = 0; i < availableItems.size(); i++) {
			System.out.print(availableItems.get(i).getrValue() + ", ");
		}
		System.out.println();
		System.out.println("-----------------");
		Collections.sort(availableItems);
		for (int i = 0; i < availableItems.size(); i++) {
			System.out.print(availableItems.get(i).getrValue() + ", ");
		}
//		for (int i = 0; i < availableItems.length; i++) {
//			for (int j = 0; j < bags.length; j++) {
//				
//			}
//		}
	}


	public void changeText(Button btn, String str) {
		btn.setText(str); 	
	}

}
