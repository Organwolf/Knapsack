package utilities;

import java.util.ArrayList;

import pojos.Bag;

public class KnapsackHelper {
	
	public static float getValueAcrossAllKnapsacks(ArrayList<Bag> bags) {
		float totalValue = 0;
		for (int i = 0; i < bags.size(); i++) {
			totalValue+=bags.get(i).getrValue();
		}
		return totalValue;
	}

}
