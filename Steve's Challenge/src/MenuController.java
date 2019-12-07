import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MenuController extends Application{
	
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button play;
    @FXML
    private Button settings;
    @FXML
    private Button myProfile;
    @FXML
    private Button leaderboard;
    @FXML
    private Button exit;
   
    /**
     * This method gets you to the load page, 
     * i.e. the page with all the profile's saves
     * 
     */
    public void changeToSaveSelectWindow(ActionEvent event) throws IOException {
    	//should be a play game function here
    	Parent root = FXMLLoader.load(getClass().getResource("loadPage.fxml"));
    	Scene scene = new Scene(root);
    	
    	Stage loadGameMenu = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	loadGameMenu.setScene(scene);
    	loadGameMenu.show();
    	
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
    	
    	popup.setTitle("Are you sure you want to exit?");
    	popup.setScene(scene);
    	popup.show();
    }
    
    /**
     * This method changes the scene to the Settings window
     * @param event
     * @throws IOException
     */
    public void changeToSettingsWindow(ActionEvent event) throws IOException {
    	
    	Parent settingsWindow = FXMLLoader.load(getClass().getResource("settings.fxml"));
    	Scene scene = new Scene(settingsWindow);
    	
    	Stage settings = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	
    	settings.setScene(scene);
    	settings.show();
    }
    
    /**
     * This method changes the scene to the Settings window
     * @param event
     * @throws IOException
    */
    public void changeToLeaderBoardWindow(ActionEvent event) throws IOException {
    	
    	Parent leaderboardWindow = FXMLLoader.load(getClass().getResource("leaderboard.fxml"));
    	Scene scene = new Scene(leaderboardWindow);
    	
    	Stage leaderboard = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	
    	
    	leaderboard.setScene(scene);
    	leaderboard.show();
    }
    
    public void changeToMyProfileWindow(ActionEvent event) throws IOException {
    	
    	Parent myProfileWindow = FXMLLoader.load(getClass().getResource("userProfile.fxml"));
    	Scene scene = new Scene(myProfileWindow);
    	
    	Stage myProfile = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	
    	
    	myProfile.setScene(scene);
    	myProfile.show();
    }
  
    public void start(Stage primaryStage) throws IOException {
    	
    	Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
    	Scene scene = new Scene(root);
    	
    	primaryStage.setScene(scene);
    	primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, keyEvent ->{
    		if(keyEvent.getCode() == KeyCode.ESCAPE) {
    			showExitDialog();
    		}
    	});
    	primaryStage.show();
    }
    
    public static void main(String[] args) {
    	launch(args);
    }
}
