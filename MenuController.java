import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;


public class MenuController extends Application{
	
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    void initialize() {

    }
   
    private Button play;
    
    private Button settings;
    
    private Button exit;
    
    private LeaderBoard leaderBoard = new LeaderBoard();
    
    /**
     * This method runs the game
     */
    public void handleButtonBasic() {
    	play.setText("wHeRe iS tHe GaMe?");
    }
    
    public void showExitDialog() {
    	
    	Button close = new Button("Go back");
    	Button exit = new Button("Exit");
    	
    	Stage popup = new Stage();
    	close.setOnAction(e -> {
    		popup.close();
    	}); 
    	
    	exit.setOnAction(e ->{
    		Platform.exit();
    	});
    	HBox hbox = new HBox(10);
    	hbox.getChildren().addAll(exit, close);
    	
    	Scene scene = new Scene(hbox, 150, 100);
    	
    	popup.setTitle("Are you ssure you want to exit?");
    	popup.setScene(scene);
    	popup.show();
    }
    
    /**
     * This method changes the scene to the Settings window
     * @param event
     * @throws IOException
     */
    public void changeToSettingsWindow(ActionEvent event) throws IOException{
    	
    	Parent settingsWindow = FXMLLoader.load(getClass().getResource("SettingsMenu.fxml"));
    	Scene scene = new Scene(settingsWindow);
    	
    	Stage settings = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	
    	settings.setScene(scene);
    	settings.show();
    }

    public void start(Stage stage) throws IOException{
    	
    	Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
    	
    	Scene scene = new Scene(root);
    	
    	stage.setScene(scene);
    	
    	stage.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent ->{
    		if(keyEvent.getCode() == KeyCode.ESCAPE) {
    			showExitDialog();
    		}
    	});
    	
    	stage.show();
    }
    
    public static void main(String[] args) {
    	launch(args);
    }
    
    
}
