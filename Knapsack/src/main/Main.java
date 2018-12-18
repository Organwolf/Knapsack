package main;
	
import controllers.KnapsackController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 * @author Filip Nilsson & Aron Polner
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		new KnapsackController(primaryStage);
	}
	
	/**
	 * Launcher of the MKS problem.
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
