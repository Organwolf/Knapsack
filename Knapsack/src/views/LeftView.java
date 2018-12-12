package views;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class LeftView extends VBox{
	private Button startBtn;
	private Button stepBtn;
	private Button resetBtn;
	
	//input controller instance
	public LeftView() {
		initButtonsInVBox();
	}

	private void initButtonsInVBox() {
		startBtn = new Button("h");
		stepBtn = new Button("j");
		resetBtn = new Button("p");
		this.getChildren().add(startBtn);
		this.getChildren().add(stepBtn);
		this.getChildren().add(resetBtn);
		//add actionListeners that call seperate methods in controller
		
	}

}
