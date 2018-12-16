package utilities;

import java.util.ArrayList;

import main.Settings;
import pojos.Bag;
import pojos.Item;

public class KnapsackHelper {
	
	public static float getValueAcrossAllKnapsacks(ArrayList<Bag> bags) {
		float totalValue = 0;
		for (int i = 0; i < bags.size(); i++) {
			totalValue+=bags.get(i).getrValue();
		}
		return totalValue;
	}
	
	public static ArrayList<Item> generateDefaultItemList(){
		ArrayList<Item> items = new ArrayList<>();
		items.add(new Item(1,9,3,9/3));
		items.add(new Item(2,9,3,9/3));
		items.add(new Item(3,30,10,30/10));
		items.add(new Item(4,6,3,6/3));
		items.add(new Item(5,6,3,6/3));
		items.add(new Item(6,6,3,6/3));
		items.add(new Item(7,6,3,6/3));
		return items;
	}

}
