import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LoginController extends Application{
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signUp;

    @FXML
    private Button signIn;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    
    private static UserProfile user = null;
    
    @FXML
    void initialize() {
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'login.fxml'.";
        assert signUp != null : "fx:id=\"signUp\" was not injected: check your FXML file 'login.fxml'.";
        assert signIn != null : "fx:id=\"signIn\" was not injected: check your FXML file 'login.fxml'.";

    }
    
    public static UserProfile getUserProfile() {
    	if(user != null) {
    		return user;
    	}else {
    		System.out.println("No user logged in");
    		return null;
    	}
    }
    

    public void handleSignIn(ActionEvent event) throws IOException{
    	
    	String usernameInput = usernameField.getText();
    	String passwordInput = passwordField.getText();
    	
    	if(!usernameInput.equals("") && !passwordInput.equals("")) {
    		if(Game.getLeaderBoard().checkLoginData(usernameInput, passwordInput)) {
    			user = Game.getLeaderBoard().search(usernameInput);
    			changeToMainMenu(event);
    		}
    	}else {
    		Button close = new Button("Go back");
        	
        	Stage popup = new Stage();
        	close.setOnAction(e -> {
        		popup.close();
        	}); 
       
        	HBox hbox = new HBox(10);
        	hbox.getChildren().addAll(close);
        	
        	Scene scene = new Scene(hbox, 400, 200);
        	
        	popup.setTitle("Please enter the other credential");
        	popup.setScene(scene);
        	popup.show();
    	}
    }
    
	public void changeToSignUpPage(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
		Scene signInPage = new Scene(root);
		
		Stage signInWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		signInWindow.setScene(signInPage);
		signInWindow.show();
		
	}
	
	public void changeToMainMenu(ActionEvent event) throws IOException{
		
		Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
		Scene menuPage = new Scene(root);

		Stage menuWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		menuWindow.setScene(menuPage);
		menuWindow.show();
	}
	
	public void start(Stage primaryStage) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene scene = new Scene(root);
	
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
