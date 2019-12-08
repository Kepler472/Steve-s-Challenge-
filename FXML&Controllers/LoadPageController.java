import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoadPageController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button newGame;

	@FXML
	private Button continueButton;

	@FXML
	private Button saveSlot1;

	@FXML
	private Button saveSlot2;

	@FXML
	private Button saveSlot3;

	@FXML
	private Button back;

	/**
	 * This method changes the scene to loading screen
	 * @param stage
	 * @throws IOException
	 */
	public void changeToLoadingScreen(Stage stage) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("loadingScreen.fxml"));
		Scene scene = new Scene(root);

		stage.setScene(scene);
		stage.show();
	}

	/**
	 * This method starts a new game
	 * @param event
	 * @throws IOException
	 */
	public void handleNewGame(ActionEvent event) throws IOException{

		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

		Launch.getGame().setFileName("1.txt");

		changeToLoadingScreen(primaryStage);
	}

	/**
	 * This method allows the user to continue the game from the last save
	 * @param event
	 * @throws IOException
	 */
	public void handleContinueButton(ActionEvent event) throws IOException{

		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

		Launch.getGame().setFileName(Launch.getGame().getUser().getLevelCompletion() + ".txt");

		changeToLoadingScreen(primaryStage);
	}

	/**
	 * This method saves the progression of the user in the slot1
	 * @param event
	 * @throws IOException
	 */
	public void handleSaveSlot1Button(ActionEvent event) throws IOException{

		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

		Launch.getGame().setFileName(Launch.getGame().getUser().getSave(1).getName());

		changeToLoadingScreen(primaryStage);
	}

	/**
	 * This method saves the progression of the user in the slot3
	 * @param event
	 * @throws IOException
	 */
	public void handleSaveSlot3Button(ActionEvent event) throws IOException{

		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

		Launch.getGame().setFileName(Launch.getGame().getUser().getSave(2).getName());

		changeToLoadingScreen(primaryStage);
	}

	/**
	 * This method saves the progression of the user in the slot2
	 * @param event
	 * @throws IOException
	 */
	public void handleSaveSlot2Button(ActionEvent event) throws IOException{

		Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

		Launch.getGame().setFileName(Launch.getGame().getUser().getSave(0).getName());

		changeToLoadingScreen(primaryStage);
	}

	/**
	 * This method changes the scene to the Main Menu window
	 * @param event
	 * @throws IOException
	 */
	public void backToMainMenu(ActionEvent event) throws IOException{

		Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
		Scene scene = new Scene(root);

		Stage  menu = (Stage) ((Node)event.getSource()).getScene().getWindow();

		menu.setScene(scene);
		menu.show();
	}
}