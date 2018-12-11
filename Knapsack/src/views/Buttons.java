package views;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

// solve CSS compatibility

public class Buttons {
	public VBox initButtons() {
		VBox VBox = new VBox();
		VBox.setPadding(new Insets(220, 20, 20, 20));
		VBox.setSpacing(40);
		Button stepButton = new Button("Step");
		Button startButton = new Button("Start");
		Button resetButton = new Button("Reset");
		VBox.getChildren().add(startButton);
		VBox.getChildren().add(resetButton);
		VBox.getChildren().add(stepButton);
		return VBox;
	}
}
