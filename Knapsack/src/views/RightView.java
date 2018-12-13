package views;

import controllers.KnapsackController;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class RightView extends VBox {
	private KnapsackController controller;
	private Text t1;
	
	public RightView(KnapsackController controller) {
		this.controller = controller;
		initTextRight();
	}
	private void initTextRight() {
		t1 = new Text();
		t1.setText("Test");
		this.getChildren().add(t1);
		// use controller to get input?
	}
}
