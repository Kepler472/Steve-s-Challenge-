import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SettingsController extends Application{
	
	@FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    void initialize() {

    }
    public static void main(String[] args) {
    	launch(args);
    }
    
    public void start(Stage primaryStage) throws IOException{
    	Parent root = FXMLLoader.load(getClass().getResource("SettingsMenu.fxml"));
    	Scene scene = new Scene(root);
    	
    	primaryStage.setScene(scene);
    	primaryStage.show();
    	
    	
    }
}
