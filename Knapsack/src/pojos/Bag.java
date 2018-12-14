package pojos;

import java.util.ArrayList;

public class Bag {
	private ArrayList<Item> items;
	private int weight;
	private int value;
	private float rvalue;
	
	public Bag() {
		items = new ArrayList<>();
		weight = 0;
		value = 0;
		rvalue = 0;
	}
	public float getrValue() {
		return rvalue;
	}
	public void setrValue(float rvalue) {
		this.rvalue = rvalue;
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
	public void addItem(Item item) {
		this.items.add(item);
		this.weight+=item.getWeight(); //Important!
		this.value+=item.getValue();   //Important!
		this.rvalue+=item.getrValue();
	}
	public void removeItem(int index) {
		this.weight-=items.get(index).getWeight(); // Important
		this.value-=items.get(index).getValue();   //Important!
		this.rvalue-=items.get(index).getrValue();
		this.items.remove(index);
		
	}
	

}
