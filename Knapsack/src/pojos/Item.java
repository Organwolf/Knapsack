package pojos;

public class Item implements Comparable<Item>{
	private int value;
	private int weight;
	private float rValue;

	public Item(int value, int weight, float rValue) {
		this.value = value;
		this.weight = weight;
		this.rValue = rValue;
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
	 * Used to sort Items in ascending order based on rValue
	 */
	@Override
	public int compareTo(Item other) {
		if(rValue>=other.rValue) return 1;
		else return -1;
	}
	
	
	

}
