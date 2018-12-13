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
	private ArrayList<Bag> bags;
	private ArrayList<Item> availableItems;
	public KnapsackController(Stage primaryStage) {
		initBags();
		knapsackView = new KnapsackView(primaryStage, this);
		knapsackView.initWindow();
		//fillKnapSacksRandomly(); //Just for testing
		generateItems();		 //Just for testing
		generateGreedySolution();
	}
	
	private void initBags() {
		bags = new ArrayList<>();
		for (int i = 0; i < Settings.NUMBER_OF_KNAPSACKS; i++) {
			bags.add(new Bag());
		}
	}
	
	private void generateItems() {
		Random rand = new Random();
		availableItems = new ArrayList<>();
		for (int i = 0; i < Settings.NUMBER_OF_ITEMS; i++) {
			int value = rand.nextInt(5)+1;
			int weight = rand.nextInt(5)+1;
			float rValue = (float)value/weight;
			availableItems.add(new Item(value, weight, rValue));
		}
		Collections.sort(availableItems);
		knapsackView.updateBottomView(availableItems);
	}
	
	public void pickGreedyItem() {
		int indexOfBagChosen = 0;
		for (int i = 0; i < availableItems.size(); i++) {
			Item tempItem = availableItems.get(i);
			boolean itemChosen = false;
			for (int j = 0; j < bags.size(); j++) {
				Bag currentBag = bags.get(j);
				int tempWeight = currentBag.getWeight() + tempItem.getWeight();
				if(tempWeight<=Settings.WEIGHT_CAPACITY) {
					currentBag.addItem(tempItem);
					itemChosen = true;
					indexOfBagChosen = j;
					break; //no need to iterate the other bags on the same Item.
				}
			}
			if(itemChosen) {
				availableItems.remove(i);
				knapsackView.updateBottomView(availableItems);
				Item itemToAdd = bags.get(indexOfBagChosen).getItems().get(bags.get(indexOfBagChosen).getItems().size()-1);
				knapsackView.addItemToKnapSack(indexOfBagChosen, itemToAdd);//get last item
				break;		//We have placed one item, method should terminate.
			}
		}
	}
	
	/**
	 * Work in progress. Algorithm could be simplified. 
	 * Sorts the item list in descending order based on rValue. Then items are picked one by one and placed into available bags until weight limit is reached.
	 */
	public void generateGreedySolution() {
		ArrayList<Item> updatedAvailableItems = new ArrayList<>();
		for (int i = 0; i < availableItems.size(); i++) {
			Item tempItem = availableItems.get(i);
			boolean itemChosen = false;
			for (int j = 0; j < bags.size(); j++) {
				Bag currentBag = bags.get(j);
				int tempWeight = currentBag.getWeight() + tempItem.getWeight();
				if(tempWeight<=Settings.WEIGHT_CAPACITY) {
					currentBag.addItem(tempItem);
					itemChosen = true;
					break; //no need to iterate the other bags on the same Item.
				}
			}
			if(!itemChosen) {
				updatedAvailableItems.add(tempItem);
			}
		}
		availableItems = updatedAvailableItems; //the not picked items
		knapsackView.updateBottomView(availableItems);
		for (int i = 0; i < bags.size(); i++) {
			for (int j = 0; j < bags.get(i).getnbrOfItems(); j++) {
				knapsackView.addItemToKnapSack(i, bags.get(i).getItems().get(j));
			}
		}
		
	}


	public void changeText(Button btn, String str) {
		btn.setText(str); 	
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

}
