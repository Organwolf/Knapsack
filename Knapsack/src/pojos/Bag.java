package pojos;

import java.util.ArrayList;

public class Bag {
	private ArrayList<Item> items;
	private int weight;
	public Bag() {
		items = new ArrayList<>();
		weight = 0;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	public int getnbrOfItems() {
		return items.size();
	}
	public void addItem(Item item) {
		this.items.add(item);
		this.weight+=item.getWeight(); //Important!
	}
	public void removeItem(int index) {
		this.items.remove(index);
	}
	

}
