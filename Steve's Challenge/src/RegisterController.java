import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterController {
	
	@FXML
	private TextField usernameField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private Button backButton;
	@FXML
	private Button signup;
	
	
	public void handleBackButtonPress(ActionEvent event) throws IOException{
		
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene signInPage = new Scene(root);
		
		Stage signInWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		signInWindow.setScene(signInPage);
		signInWindow.show();
	}
	
	public void changeToMainMenu(ActionEvent event) throws IOException{
		
		Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
		Scene signInPage = new Scene(root);
		
		Stage signInWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		signInWindow.setScene(signInPage);
		signInWindow.show();
	}
	
	public void handleSignUpRequest(ActionEvent event) {
		
		if(Game.getLeaderBoard().hasProfile(usernameField.getText())) {
			
			Button close = new Button("Go back");
        	Label existingUserText = new Label("The username is already taken! \n"
        			+ "Pick another username or check if you have a profile!");
        	
        	Stage popup = new Stage();
        	close.setOnAction(e -> {
        		popup.close();
        	}); 
       
        	VBox vbox = new VBox(10);
        	vbox.getChildren().addAll(existingUserText,close);
        	vbox.setAlignment(Pos.CENTER);
        	
        	Scene scene = new Scene(vbox, 400, 200);
        	
        	popup.setTitle("User existent");
        	popup.setScene(scene);
        	popup.show();
        	
        	event.consume();
		}else {
			UserProfile newUser = new UserProfile(usernameField.getText(), passwordField.getText());
			Game.getLeaderBoard().addUser(newUser);
			
			Button close = new Button("Great!");
        	Label existingUserText = new Label("You successfully signed in! You can now start start the game!");
        	
        	Stage popup = new Stage();
        	close.setOnAction(e -> {
        		try{
        			changeToMainMenu(event);
        		}catch(IOException exception) {
        			System.out.println("Something's wrong boyo");
        		}
        		popup.close();
        	}); 
       
        	VBox vbox = new VBox(10);
        	
        	vbox.getChildren().addAll(existingUserText,close);
        	vbox.setAlignment(Pos.CENTER);
        	
        	Scene scene = new Scene(vbox, 400, 200);
        	
        	popup.setTitle("User existent");
        	popup.setScene(scene);
        	popup.show();
		}
	}
	
}
