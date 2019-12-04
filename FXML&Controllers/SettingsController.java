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
    private Button keyBinding;
    @FXML 
    private Button back;
    
    @FXML
    void initialize() {

    }
    public static void main(String[] args) {
    	launch(args);
    }
    
    /**
     * This method changes the scene to the Settings window
     * @param event
     * @throws IOException
     */
    public void changeToKeyBindingWindow(ActionEvent event) throws IOException{
    	
    	Parent bindingsWindow = FXMLLoader.load(getClass().getResource("keybindings.fxml"));
    	Scene scene = new Scene(bindingsWindow);
    	
    	Stage  bindings = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	
    	bindings.setScene(scene);
    	bindings.show();
    }
    public void backToMainMenu(ActionEvent event) throws IOException{
    	
    	Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
    	Scene scene = new Scene(root);
    	
    	Stage  menu = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	
    	menu.setScene(scene);
    	menu.show();
    }
    
    public void start(Stage primaryStage) throws IOException{
    	Parent root = FXMLLoader.load(getClass().getResource("settings.fxml"));
    	Scene scene = new Scene(root);
    	
    	primaryStage.setScene(scene);
    	primaryStage.show();
    }
}
