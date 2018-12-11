package main;
	
import controllers.Knapsack;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		new Knapsack(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
