package utilities;

import java.util.ArrayList;
import java.util.Random;
import main.Settings;
import pojos.Bag;
import pojos.Item;

public class KnapsackHelper {
	
	public static float getrValueAcrossAllKnapsacks(ArrayList<Bag> bags) {
		float totalValue = 0;
		for (int i = 0; i < bags.size(); i++) {
			totalValue+=bags.get(i).getrValue();
		}
		return totalValue;
	}
	
	public static float getValueAcrossAllKnapsacks(ArrayList<Bag> bags) {
		float totalValue = 0;
		for (int i = 0; i < bags.size(); i++) {
			totalValue+=bags.get(i).getValue();
		}
		return totalValue;
	}
	
	public static ArrayList<Item> generateRandomItemList(){
		ArrayList<Item> items = new ArrayList<>();
		Random rand = new Random();
		for (int i = 0; i < Settings.NUMBER_OF_ITEMS; i++) {
			int value = rand.nextInt(Settings.RND_INTERVAL)+1;
			int weight = rand.nextInt(Settings.RND_INTERVAL)+1;
			float rValue = (float)value/weight;
			items.add(new Item(i+1, value, weight, rValue));
		}
		return items;
	}
	
	public static ArrayList<Item> generateDefaultItemList(){
		ArrayList<Item> items = new ArrayList<>();
		items.add(new Item(1,1,1,1/1));
		items.add(new Item(2,2,1,2/1));
//		items.add(new Item(3,3,1,3/1));
		items.add(new Item(4,4,1,4/1));
//		items.add(new Item(5,5,1,5/1));
//		items.add(new Item(6,6,1,6/1));
//		items.add(new Item(7,7,1,7/1));
//		items.add(new Item(1,9,3,9/3));
//		items.add(new Item(2,9,3,9/3));
//		items.add(new Item(3,30,10,30/10));
//		items.add(new Item(4,6,3,6/3));
//		items.add(new Item(5,6,3,6/3));
//		items.add(new Item(6,6,3,6/3));
//		items.add(new Item(7,6,3,6/3));
		return items;
	}

	public static ArrayList<Item> getItemListCopy(ArrayList<Item> items){
		ArrayList<Item> tempItems = new ArrayList<>();
		for (int i = 0; i < items.size(); i++) {
			int index = items.get(i).getIndex();
			int value = items.get(i).getValue();
			int weight = items.get(i).getWeight();
			float rValue = items.get(i).getrValue();
			Item item = new Item(index, value, weight, rValue);
			tempItems.add(item);
		}
		return tempItems;
	}
	
	public static ArrayList<Bag> getBagListCopy(ArrayList<Bag> bags){
		ArrayList<Bag> tempBags = new ArrayList<>();
		for (int i = 0; i < bags.size(); i++) {
			int value = bags.get(i).getValue();
			int weight = bags.get(i).getWeight();
			float rValue = bags.get(i).getrValue();
			ArrayList<Item> items = getItemListCopy(bags.get(i).getItems());
			Bag bag = new Bag();
			bag.setValue(value);
			bag.setWeight(weight);
			bag.setrValue(rValue);
			bag.setItems(items);
			tempBags.add(bag);
		}
		return tempBags;
	}
}
