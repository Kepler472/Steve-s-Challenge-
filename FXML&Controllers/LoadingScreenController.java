import java.util.concurrent.ThreadLocalRandom;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoadingScreenController {

	@FXML
	private Button go;
	
	@FXML
	private Label message;
	
	private String[] messageOfTheDay = {"Pigs","Liam","Dan","Kitchen Sink"};
	
	@FXML
	void initialize() {
		int randomNum = ThreadLocalRandom.current().nextInt(0, 4);
		message.setText(messageOfTheDay[randomNum]);
	}
	
	public void handleGoButton(ActionEvent event) {
		
		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();		
		Launch.getGame().start(primaryStage);
	}
	
}
