package views;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ItemView extends VBox{
	public ItemView(String value, String weight, String rValue) {
		this.getChildren().add(new Text("Value: "+ value));
		this.getChildren().add(new Text("Weight: "+ weight));
		this.getChildren().add(new Text("r-value: "+ rValue));
		
	    String cssLayout = "-fx-border-color: black;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 3;\n" +
                "-fx-border-style: solid;\n";
	    this.setStyle(cssLayout);
	}

} 
