package views;

import controllers.KnapsackController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import main.Settings;
import utilities.KnapsackHelper;

public class LeftView extends VBox{
	private Button solveBtn;
	private KnapsackController controller;
	
	//input controller instance
	public LeftView(KnapsackController controller) {
		this.controller = controller;
		initButtonsInVBox();
	}

	private void initButtonsInVBox() {
		solveBtn = new Button("Solve  ");
		this.getChildren().add(solveBtn);
		
//		startBtn.setOnAction((ActionEvent event)->System.out.println("start"));
//		stepBtn.setOnAction((ActionEvent event)->System.out.println("step"));
//		resetBtn.setOnAction((ActionEvent event)->System.out.println("reset"));
				
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
