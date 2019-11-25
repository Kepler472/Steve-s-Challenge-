import java.io.IOException;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginController extends Application{

	public Button signIn;
	public Button signUp;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void changeToSignInPage(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("login"));
		Scene signInPage = new Scene(root);
		
		primaryStage.setScene(signInPage);
		primaryStage.show();
		
	}
	
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
