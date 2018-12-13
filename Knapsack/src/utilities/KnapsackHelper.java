package utilities;

import java.util.ArrayList;

import pojos.Bag;
import pojos.Item;

public class KnapsackHelper {
	
	public static int getValueAcrossAllKnapsacks(ArrayList<Bag> bags) {
		int totalValue = 0;
		for (int i = 0; i < bags.size(); i++) {
			totalValue+=bags.get(i).getValue();
		}
		return totalValue;
	}

}
