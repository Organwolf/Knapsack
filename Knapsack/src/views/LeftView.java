package views;

import controllers.Knapsack;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

public class LeftView extends VBox{
	private Button startBtn;
	private Button stepBtn;
	private Button resetBtn;
	private Knapsack controller;
	
	//input controller instance
	public LeftView(Knapsack controller) {
		
		// created when trying to incorporate the controller
		this.controller = controller;
		
		initButtonsInVBox();
	}

	private void initButtonsInVBox() {
		startBtn = new Button("Start  ");
		stepBtn = new Button("Step   ");
		resetBtn = new Button("Reset ");
		this.getChildren().add(startBtn);
		this.getChildren().add(stepBtn);
		this.getChildren().add(resetBtn);
		
//		startBtn.setOnAction((ActionEvent event)->System.out.println("start"));
//		stepBtn.setOnAction((ActionEvent event)->System.out.println("step"));
//		resetBtn.setOnAction((ActionEvent event)->System.out.println("reset"));
		
		// How the fuck do I get this into the controller?
		//controller.changeText(startBtn, "hello");			
		EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                startBtn.setText("hello"); 
            } 
        };
        startBtn.setOnAction(event1);
		EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                stepBtn.setText("world"); 
            } 
        };
        stepBtn.setOnAction(event2);
		EventHandler<ActionEvent> event3 = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
                resetBtn.setText("!!!!!"); 
            } 
        };
        resetBtn.setOnAction(event3);
	}

}
