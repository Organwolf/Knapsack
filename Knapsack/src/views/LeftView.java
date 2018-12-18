package views;

import controllers.KnapsackController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class LeftView extends VBox{
	private Button solveBtn;
	private KnapsackController controller;
	
	public LeftView(KnapsackController controller) {
		this.controller = controller;
		initButtonsInVBox();
	}

	private void initButtonsInVBox() {
		solveBtn = new Button("Solve  ");
		this.getChildren().add(solveBtn);
				
		EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            		controller.oneByTwoSearch();
            		controller.twoByOneSearch();
            		controller.oneByOneSearch();
            		controller.generateStupidSolution();     		
            } 
        };
        solveBtn.setOnAction(event1);     
	}
}
