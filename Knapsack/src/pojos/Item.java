package pojos;

public class Item implements Comparable<Item>{
	private int value;
	private int weight;
	private float rValue;
	private int index;

	public Item(int index, int value, int weight, float rValue) {
		this.index = index;
		this.value = value;
		this.weight = weight;
		this.rValue = rValue;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public String toString() {
		return "Item [value=" + value + ", weight=" + weight + ", rValue=" + rValue + ", index=" + index + "]";
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

	public float getrValue() {
		return rValue;
	}

	public void setrValue(float rValue) {
		this.rValue = rValue;
	}

	/**
	 * Used to sort Items in descending order based on rValue
	 */
	@Override
	public int compareTo(Item other) {
		if(rValue<=other.rValue) return 1;
		else return -1;
	}
	
	
	

}
