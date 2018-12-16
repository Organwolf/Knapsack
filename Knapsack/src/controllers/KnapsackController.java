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
import utilities.KnapsackHelper;
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
		generateDefaultItems();
		//generateStupidSolution();
		generateGreedySolution();
		//generateItems();	
	}
	
	private void initBags() {
		bags = new ArrayList<>();
		for (int i = 0; i < Settings.NUMBER_OF_KNAPSACKS; i++) {
			bags.add(new Bag());
		}
	}
	
	private void generateDefaultItems() {
		availableItems = KnapsackHelper.generateDefaultItemList();
		Collections.sort(availableItems);
		knapsackView.updateBottomView(availableItems);
	}
	
	private void generateItems() {
		Random rand = new Random();
		availableItems = new ArrayList<>();
		for (int i = 0; i < Settings.NUMBER_OF_ITEMS; i++) {
			int value = rand.nextInt(5)+1;
			int weight = rand.nextInt(5)+1;
			float rValue = (float)value/weight;
			availableItems.add(new Item(i+1,value, weight, rValue));
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
					currentBag.addLast(tempItem);
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
		knapsackView.updateRightView(KnapsackHelper.getValueAcrossAllKnapsacks(bags));
	}
	
	/**
	 * Work in progress. Algorithm could be simplified. 
	 * Sorts the item list in descending order based on rValue. 
	 * Then items are picked one by one and placed into available 
	 * bags until weight limit is reached.
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
					currentBag.addLast(tempItem);
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
		knapsackView.updateRightView(KnapsackHelper.getValueAcrossAllKnapsacks(bags));
		//System.out.println(KnapsackHelper.getValueAcrossAllKnapsacks(bags));
	}
	
	public void generateStupidSolution() {
		// Add the first item to the first bag and then remove it
		Item firstItem = availableItems.get(0);
		availableItems.remove(0);
		Bag bag1 = bags.get(0);
		bag1.addFirst(firstItem);
		System.out.println(bag1.getWeight());
		// Update view
		knapsackView.updateBottomView(availableItems);
		knapsackView.addItemToKnapSack(0, bags.get(0).getItems().get(0));
		knapsackView.updateRightView(KnapsackHelper.getValueAcrossAllKnapsacks(bags));
	}
	
	public void searchNeighborhood(int depth) {		
		// insert logic for the neighborhood
		int knapsacks = Settings.NUMBER_OF_KNAPSACKS;
		Bag currentKnap;
		int nbrOfItems;
		int weightDiff;
		Item currentItem;
		
		// depth neightborhood search
		for (int i = 0; i < depth; i++) {
			for (int j = 0; j < 1; j++) {			// for each bag
				currentKnap = bags.get(j);
				nbrOfItems = availableItems.size();
				weightDiff = Settings.WEIGHT_CAPACITY - currentKnap.getWeight();
				

				if(!availableItems.isEmpty()) {
					System.out.println(availableItems.toString());
					currentItem = availableItems.get(0);
					
					// add item
					if(currentItem.getWeight() < weightDiff) {
						currentKnap.addFirst(currentItem);
						availableItems.remove(0);
					}
					// remove and add item
					else {
						if (!currentKnap.getItems().isEmpty()) {
							// and don't want to just remove it 
							// I alsa want to return it to the available items!!
							currentKnap.removeLast();
						}
					}
					
					
					// Update view
					knapsackView.clearAllViews();
					for(int p=0;p<currentKnap.getnbrOfItems();p++) {
						knapsackView.addItemToKnapSack(0, bags.get(0).getItems().get(p));
					}
					knapsackView.updateBottomView(availableItems);
					
					knapsackView.updateRightView(KnapsackHelper.getValueAcrossAllKnapsacks(bags));
				}
				//System.out.println(currentKnap.toString());
				
				// else remove the last item and add the new item
				// store the removed item and repeat the process with the next bag
			}			
		}
	}
}
