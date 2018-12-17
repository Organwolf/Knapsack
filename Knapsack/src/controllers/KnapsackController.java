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
import views.BottomView;
import views.CenterView;
import views.KnapsackView;

// Controller class for the Knapsack

public class KnapsackController {
	private KnapsackView knapsackView;
	private ArrayList<Bag> bags;
	private ArrayList<Item> availableItems;

	public KnapsackController(Stage primaryStage) {
		knapsackView = new KnapsackView(primaryStage, this);
		knapsackView.initWindow();
		//generateDefaultItems();
		generateRandomItems();
		initBags();
		// generateStupidSolution();
		generateGreedySolution();
	}

	private void initBags() {
		bags = new ArrayList<>();
		for (int i = 0; i < Settings.NUMBER_OF_KNAPSACKS; i++) {
			bags.add(new Bag());
		}
	}

	private void generateDefaultItems() {
		availableItems = KnapsackHelper.generateDefaultItemList();
		// Collections.sort(availableItems);
		knapsackView.updateBottomView(availableItems);
	}

	private void generateRandomItems() {
		availableItems = KnapsackHelper.generateRandomItemList();
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
				if (tempWeight <= Settings.WEIGHT_CAPACITY) {
					currentBag.addLast(tempItem);
					itemChosen = true;
					indexOfBagChosen = j;
					break; // no need to iterate the other bags on the same Item.
				}
			}
			if (itemChosen) {
				availableItems.remove(i);
				knapsackView.updateBottomView(availableItems);
				Item itemToAdd = bags.get(indexOfBagChosen).getItems()
						.get(bags.get(indexOfBagChosen).getItems().size() - 1);
				knapsackView.addItemToKnapSack(indexOfBagChosen, itemToAdd);// get last item
				break; // We have placed one item, method should terminate.
			}
		}
		knapsackView.updateRightView(KnapsackHelper.getrValueAcrossAllKnapsacks(bags));
	}

	/**
	 * Work in progress. Algorithm could be simplified. Sorts the item list in
	 * descending order based on rValue. Then items are picked one by one and placed
	 * into available bags until weight limit is reached.
	 */
	public void generateGreedySolution() {
		ArrayList<Item> updatedAvailableItems = new ArrayList<>();
		for (int i = 0; i < availableItems.size(); i++) {
			Item tempItem = availableItems.get(i);
			boolean itemChosen = false;
			for (int j = 0; j < bags.size(); j++) {
				Bag currentBag = bags.get(j);
				int tempWeight = currentBag.getWeight() + tempItem.getWeight();
				if (tempWeight <= Settings.WEIGHT_CAPACITY) {
					currentBag.addLast(tempItem);
					itemChosen = true;
					break; // no need to iterate the other bags on the same Item.
				}
			}
			if (!itemChosen) {
				updatedAvailableItems.add(tempItem);
			}
		}
		availableItems = updatedAvailableItems; // the not picked items
		knapsackView.updateBottomView(availableItems);
		for (int i = 0; i < bags.size(); i++) {
			for (int j = 0; j < bags.get(i).getnbrOfItems(); j++) {
				knapsackView.addItemToKnapSack(i, bags.get(i).getItems().get(j));
			}
		}
		knapsackView.updateRightView(KnapsackHelper.getrValueAcrossAllKnapsacks(bags));
		// System.out.println(KnapsackHelper.getValueAcrossAllKnapsacks(bags));
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
		knapsackView.updateRightView(KnapsackHelper.getrValueAcrossAllKnapsacks(bags));
	}

	public void searchNeighborhood(int neighbors) {
		Bag firstKnapsack = bags.get(0);
		Bag secondKnapsack = bags.get(1);
		Item currentItem;
		Item nextItem;
		int wDiff;
		float bestRelative = 0;
		float currentRelative;

		// number of neighbors created/checked
		for (int neighbor = 0; neighbor < neighbors; neighbor++) {

			// check if first knapsack has items
			if (!firstKnapsack.getItems().isEmpty()) {
				currentItem = firstKnapsack.getLastItem();

				// calculate weight cap. left in second knapsack
				wDiff = Settings.WEIGHT_CAPACITY - secondKnapsack.getWeight();

				// check if currentItem fits in the second knapsack
				// if not remove item(s) in second knapsack
				while (wDiff < currentItem.getWeight()) {
					nextItem = secondKnapsack.getLastItem();
					secondKnapsack.removeLast();
					availableItems.add(nextItem);
					wDiff = Settings.WEIGHT_CAPACITY - secondKnapsack.getWeight();
				}

				// while there is room fill the second knapsack up
				while (wDiff >= currentItem.getWeight()) {
					secondKnapsack.addFirst(currentItem);
					firstKnapsack.removeLast();
					wDiff = Settings.WEIGHT_CAPACITY - secondKnapsack.getWeight();
					if (!firstKnapsack.getItems().isEmpty()) {
						currentItem = firstKnapsack.getLastItem();
					} else {
						break;
					}
				}
			}

			// pick the next available item
			if (!availableItems.isEmpty()) {
				currentItem = availableItems.get(0);
				wDiff = Settings.WEIGHT_CAPACITY - firstKnapsack.getWeight();

				// fill the first knapsack if needed/possible
				while (wDiff >= currentItem.getWeight()) {
					availableItems.remove(0);
					firstKnapsack.addFirst(currentItem);
					wDiff = Settings.WEIGHT_CAPACITY - firstKnapsack.getWeight();
					if (!availableItems.isEmpty()) {
						currentItem = availableItems.get(0);
					} else {
						break;
					}
				}
			}

			// check current relative against current best
			currentRelative = firstKnapsack.getrValue() + secondKnapsack.getrValue();
			System.out.println(currentRelative);
			if (bestRelative < currentRelative) {
				bestRelative = currentRelative; // should I save away the bags?
			}
		}

		// print best relative after iterating through neighbors
		System.out.println("Best relative value in this neighborhood: " + bestRelative);
		knapsackView.updateKnapSacks(bags);

		// needs more testing
		// consider adding the GUI at this point

//				if(!availableItems.isEmpty()) {
//					System.out.println(availableItems.toString());
//					currentItem = availableItems.get(0);
//					
//					// add item
//					if(currentItem.getWeight() < weightDiff) {
//						currentKnap.addFirst(currentItem);
//						availableItems.remove(0);
//					}
//					// remove and add item
//					else {
//						if (!currentKnap.getItems().isEmpty()) {
//							// and don't want to just remove it 
//							// I alsa want to return it to the available items!!
//							currentKnap.removeLast();
//						}
//					}

		// Update view
//					knapsackView.clearAllViews();
//					for(int p=0;p<currentKnap.getnbrOfItems();p++) {
//						knapsackView.addItemToKnapSack(0, bags.get(0).getItems().get(p));
//					}
//					knapsackView.updateBottomView(availableItems);
//					
//					knapsackView.updateRightView(KnapsackHelper.getValueAcrossAllKnapsacks(bags));

		// System.out.println(currentKnap.toString());

		// else remove the last item and add the new item
		// store the removed item and repeat the process with the next bag
	}

	public void searchNh() {
		// for (int i = 0; i < Settings.NEIGHBOR_ITERATIONS; i++) {
		// skapa arraylist kopia av bags genom deep copy
		ArrayList<Bag> tempBags = KnapsackHelper.getBagListCopy(bags);
		// skapa arraylist kopia av items genom deep copy
		ArrayList<Item> tempItems = KnapsackHelper.getItemListCopy(availableItems);
		availableItems.get(0).setValue(100);
		for (int i = 0; i < availableItems.size(); i++) {
			System.out.println("original value: " + availableItems.get(i).getValue());
		}
		for (int i = 0; i < tempItems.size(); i++) {
			System.out.println("copy value: " + tempItems.get(i).getValue());
		} // I was here!

		// }
	}

	public void oneByOneSearch() {
		for (int i = 0; i < availableItems.size(); i++) {
			float bestDiff = 0;
			int swapBagIndex = -1;
			int swapBagItemIndex = -1;
			Item itemToInsert = availableItems.get(i);
			for (int j = 0; j < bags.size(); j++) {
				Bag tempBag = bags.get(j);
				for (int k = 0; k < tempBag.getnbrOfItems(); k++) {
					// swap items i and k in bag j.
					// If possible + better solution. update best.
					Item currentItem = tempBag.getItems().get(k);
					float currentDiff = itemToInsert.getrValue() - currentItem.getrValue();
					if (currentDiff > bestDiff) {
						int totalWeight = tempBag.getWeight() + itemToInsert.getWeight() - currentItem.getWeight();
						if (totalWeight <= Settings.WEIGHT_CAPACITY) {
							swapBagIndex = j;
							swapBagItemIndex = k;
							bestDiff = currentDiff;
						}
					}
				}
			}
			if (!(swapBagIndex == -1)) {
				Bag bagToBeModified = bags.get(swapBagIndex);
				Item itemTobeRemoved = bagToBeModified.removeItem(swapBagItemIndex);
				// bagToBeModified.addItem(swapBagItemIndex, itemToInsert);
				bagToBeModified.addFirst(itemToInsert);
				availableItems.set(i, itemTobeRemoved);
			}

		}
		for (int p = 0; p < bags.size(); p++) {
			System.out.println(bags.get(p).toString());
		}
		knapsackView.updateBottomView(availableItems);
		knapsackView.updateKnapSacks(bags);
		knapsackView.updateRightView(KnapsackHelper.getrValueAcrossAllKnapsacks(bags));
	}

	public void oneByTwoSearch() {
		int availIndex1 = 0;
		int availIndex2 = 0;
		for (int i = 0; i < availableItems.size(); i++) {
			Item itemToInsert1 = availableItems.get(i);
			float bestDiff = 0;
			int swapBagIndex = -1;
			int swapBagItemIndex = -1;
			for (int i2 = i+1; i2 < availableItems.size(); i2++) {
				Item itemToInsert2 = availableItems.get(i2);
				for (int j = 0; j < bags.size(); j++) {
					Bag tempBag = bags.get(j);
					for (int k = 0; k < tempBag.getnbrOfItems(); k++) {
						// swap items i and k in bag j.
						// If possible + better solution. update best.
						Item currentItem = tempBag.getItems().get(k);
						float currentDiff = itemToInsert1.getrValue() + itemToInsert2.getrValue() - currentItem.getrValue();
						if (currentDiff > bestDiff) {
							int totalWeight = tempBag.getWeight() + itemToInsert1.getWeight() + itemToInsert2.getWeight() - currentItem.getWeight();
							if (totalWeight <= Settings.WEIGHT_CAPACITY) {
								availIndex1 = i;
								availIndex2 = i2;
								swapBagIndex = j;
								swapBagItemIndex = k;
								bestDiff = currentDiff;
							}
						}
					}
				}
			}
			if (!(swapBagIndex == -1)) {
				Bag bagToBeModified = bags.get(swapBagIndex);
				Item itemTobeRemoved = bagToBeModified.removeItem(swapBagItemIndex);
				// bagToBeModified.addItem(swapBagItemIndex, itemToInsert);
				bagToBeModified.addFirst(availableItems.get(availIndex1));
				bagToBeModified.addFirst(availableItems.get(availIndex2));
				availableItems.set(availIndex1, itemTobeRemoved);
				//We need to remove availIndex2 from availableItems
			}
		}
		for (int p = 0; p < bags.size(); p++) {
			System.out.println(bags.get(p).toString());
		}
		knapsackView.updateBottomView(availableItems);
		knapsackView.updateKnapSacks(bags);
		knapsackView.updateRightView(KnapsackHelper.getrValueAcrossAllKnapsacks(bags));
	}
}
