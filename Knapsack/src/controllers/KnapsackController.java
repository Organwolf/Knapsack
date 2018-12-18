package controllers;

import java.util.ArrayList;
import java.util.Collections;
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
		knapsackView = new KnapsackView(primaryStage, this);
		knapsackView.initWindow();
		//generateDefaultItems();
		generateRandomItems();
		initBags();
		generateStupidSolution();
		//generateGreedySolution();
	}

	private void initBags() {
		bags = new ArrayList<>();
		for (int i = 0; i < Settings.NUMBER_OF_KNAPSACKS; i++) {
			bags.add(new Bag());
		}
	}

	private void generateDefaultItems() {
		availableItems = KnapsackHelper.generateDefaultItemList();
		knapsackView.updateBottomView(availableItems);
	}

	private void generateRandomItems() {
		availableItems = KnapsackHelper.generateRandomItemList();
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

	public void generateGreedySolution() {
		ArrayList<Item> updatedAvailableItems = new ArrayList<>();
		Collections.sort(availableItems);
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
		knapsackView.updateRightView(KnapsackHelper.getValueAcrossAllKnapsacks(bags));
	}

	public void generateStupidSolution() {
		for (int i = 0; i< availableItems.size(); i++) {
			for (int j = 0; j < bags.size(); j++) {
				if(bags.get(j).getWeight() + availableItems.get(i).getWeight() <= Settings.WEIGHT_CAPACITY ) {
					bags.get(j).addLast(availableItems.get(i));
					availableItems.remove(i);
					i--;
					break;
				}
			}
		}
		// Update view
		knapsackView.updateBottomView(availableItems);
		knapsackView.updateRightView(KnapsackHelper.getValueAcrossAllKnapsacks(bags));
		knapsackView.updateKnapSacks(bags);
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
					Item currentItem = tempBag.getItems().get(k);
					float currentDiff = itemToInsert.getValue() - currentItem.getValue();
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
				bagToBeModified.addFirst(itemToInsert);
				availableItems.set(i, itemTobeRemoved);
			}
		}
		knapsackView.updateBottomView(availableItems);
		knapsackView.updateKnapSacks(bags);
		knapsackView.updateRightView(KnapsackHelper.getValueAcrossAllKnapsacks(bags));
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
						Item currentItem = tempBag.getItems().get(k);
						float currentDiff = itemToInsert1.getValue() + itemToInsert2.getValue() - currentItem.getValue();
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
				bagToBeModified.addFirst(availableItems.get(availIndex1));
				bagToBeModified.addFirst(availableItems.get(availIndex2));
				availableItems.set(availIndex1, null); //Don't want to mess upp index i.
				availableItems.set(availIndex2, itemTobeRemoved);
			}
		}
		for (int i = 0; i < availableItems.size(); i++) {
			if(availableItems.get(i)==null) {
				availableItems.remove(i);
				i--;
			}
		}
		knapsackView.updateBottomView(availableItems);
		knapsackView.updateKnapSacks(bags);
		knapsackView.updateRightView(KnapsackHelper.getValueAcrossAllKnapsacks(bags));
	}

	public void twoByOneSearch() {
		int availIndex = 0;
		for (int i = 0; i < availableItems.size(); i++) {
			Item itemToInsert1 = availableItems.get(i);
			float bestDiff = 0;
			int swapBagIndex = -1;
			int swapItemIndex1 = -1;
			int swapItemIndex2 = -1;
				for (int j = 0; j < bags.size(); j++) {
					Bag tempBag = bags.get(j);
					for (int k = 0; k < tempBag.getnbrOfItems(); k++) {
						for (int l = 0; l < tempBag.getnbrOfItems(); l++) {
							if (k != l) {
								Item currentItem1 = tempBag.getItems().get(k);
								Item currentItem2 = tempBag.getItems().get(l);
								float currentDiff = itemToInsert1.getValue() - currentItem1.getValue() - currentItem2.getValue();
								if (currentDiff > bestDiff) {
									int totalWeight = tempBag.getWeight() + itemToInsert1.getWeight() - currentItem1.getWeight() - currentItem2.getWeight();
									if (totalWeight <= Settings.WEIGHT_CAPACITY) {
										availIndex = i;
										swapBagIndex = j;
										swapItemIndex1 = k;
										swapItemIndex2 = l;
										bestDiff = currentDiff;
									}
								}
							}
						}
					}
				}

			if (!(swapBagIndex == -1)) {
				Bag bagToBeModified = bags.get(swapBagIndex);
				Item itemTobeRemoved1;
				Item itemTobeRemoved2;
				if (swapItemIndex1>swapItemIndex2) {
					itemTobeRemoved1 = bagToBeModified.removeItem(swapItemIndex1);
					itemTobeRemoved2 = bagToBeModified.removeItem(swapItemIndex2);
				}
				else {
					itemTobeRemoved2 = bagToBeModified.removeItem(swapItemIndex2);
					itemTobeRemoved1 = bagToBeModified.removeItem(swapItemIndex1);
				}

				bagToBeModified.addFirst(availableItems.get(availIndex));
				availableItems.set(availIndex, itemTobeRemoved1);
				availableItems.add(itemTobeRemoved2);
			}
		}
		knapsackView.updateBottomView(availableItems);
		knapsackView.updateKnapSacks(bags);
		knapsackView.updateRightView(KnapsackHelper.getValueAcrossAllKnapsacks(bags));
	}
}
