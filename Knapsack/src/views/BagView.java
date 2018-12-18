package views;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pojos.Item;

public class BagView extends VBox{
	private String bagName;
	private String cssLayout;
	
	public BagView(String bagName) {
		this.bagName = bagName;
	    //10/10 design!
	    cssLayout = "-fx-border-color: red;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 3;\n" +
                "-fx-border-style: dashed;\n";
	    this.setStyle(cssLayout);
	    
		Text title = new Text(this.bagName);
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    this.getChildren().add(title);
	}
	
	public void addItem(Item item) {
		this.getChildren().add(new ItemView(String.valueOf(item.getValue()), 
				String.valueOf(item.getWeight()), 
				String.valueOf(item.getrValue())));
	}
	
	public void removeAllItems() {
		this.getChildren().clear(); //Clear all childrenNodes.
		cssLayout = "-fx-border-color: red;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 3;\n" +
                "-fx-border-style: dashed;\n";
	    this.setStyle(cssLayout);
	    
		Text title = new Text(bagName);
	    title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
	    this.getChildren().add(title);
	}

}
