package views;

import java.util.ArrayList;

import javafx.scene.layout.HBox;
import pojos.Item;

public class BottomView extends HBox{
	public BottomView() {
		//10/10 design!
	    String cssLayout = "-fx-border-color: Green;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 3;\n" +
                "-fx-border-style: dashed;\n";
	    this.setStyle(cssLayout);
	}
	
	public void addItem(Item item) {
		this.getChildren().add(new ItemView(String.valueOf(item.getValue()), 
				String.valueOf(item.getWeight()), 
				String.valueOf(item.getrValue())));
	}
	
	public void addAll(ArrayList<Item> items, boolean removePrevItems) {
		if(removePrevItems) {
			removeAllItems();
		}
		for (int i = 0; i < items.size(); i++) {
			addItem(items.get(i));
		}
	}
	
	public void removeAllItems() {
		this.getChildren().clear(); //Clear all childrenNodes.
	}

}
