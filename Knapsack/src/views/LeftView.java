package views;

import controllers.KnapsackController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import main.Settings;

public class LeftView extends VBox{
	private Button solveBtn;
	private Button stepBtn;
	private KnapsackController controller;
	
	//input controller instance
	public LeftView(KnapsackController controller) {
		this.controller = controller;
		initButtonsInVBox();
	}

	private void initButtonsInVBox() {
		solveBtn = new Button("Solve  ");
		stepBtn = new Button("Step   ");
		this.getChildren().add(solveBtn);
		this.getChildren().add(stepBtn);
		
//		startBtn.setOnAction((ActionEvent event)->System.out.println("start"));
//		stepBtn.setOnAction((ActionEvent event)->System.out.println("step"));
//		resetBtn.setOnAction((ActionEvent event)->System.out.println("reset"));
				
		EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                //controller.searchNeighborhood(Settings.NEIGHBOR_ITERATIONS);
            	controller.oneByOneSearch();
            } 
        };
        solveBtn.setOnAction(event1);
        
		EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            {
            		solveBtn.setDisable(true);
            		controller.pickGreedyItem();
            } 
        };
        stepBtn.setOnAction(event2);
        
	}
	
	

}
