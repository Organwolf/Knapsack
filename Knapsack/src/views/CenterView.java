package views;

import javafx.scene.layout.HBox;
import main.Settings;
import pojos.Item;

public class CenterView extends HBox{
	public CenterView() {
		initBagsInHBox();
	} 
	
	private void initBagsInHBox() {
		int nbrOfBags = Settings.NUMBER_OF_KNAPSACKS;
		for (int i = 0; i < nbrOfBags; i++) {
			this.getChildren().add(new BagView("Knapsack "+ String.valueOf(i)));
		}
	}
	
	public void addItemToKnapSack(int bagIndex, Item item) {
		BagView bagView = (BagView) this.getChildren().get(bagIndex);
		bagView.addItem(item);
	}

}
