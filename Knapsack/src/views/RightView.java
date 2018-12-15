package views;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RightView extends VBox{
	private Text t1;
	
	public RightView() {
		initTextRight();
	}
	private void initTextRight() {
		t1 = new Text();
		this.getChildren().add(t1);
	}
	public void setTextValue(float f){
		t1.setText("Total Relative value: " + Float.toString(f));
	}

}
