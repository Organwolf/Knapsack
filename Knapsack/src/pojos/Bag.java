package pojos;

import java.util.ArrayList;

public class Bag {
	private ArrayList<Item> items;
	private int weight;
	private int value;
	private float rValue;
	public Bag() {
		items = new ArrayList<>();
		weight = 0;
		value = 0;
		rValue = 0;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
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
//	public void setItems(ArrayList<Item> items) {
//		this.items = items;
//	}
	
	public int getnbrOfItems() {
		return items.size();
	}
	public float getrValue() {
		return rValue;
	}
	public void setrValue(float rValue) {
		this.rValue = rValue;
	}
	public void addItem(Item item) {
		this.items.add(item);
		this.weight+=item.getWeight(); //Important!
		this.value+=item.getValue();   //Important!
		this.rValue+=item.getrValue();
	}
	public void removeItem(int index) {
		this.weight-=items.get(index).getWeight(); // Important
		this.value-=items.get(index).getValue();   //Important!
		this.rValue-=items.get(index).getrValue();
		this.items.remove(index);
	}
	

}
