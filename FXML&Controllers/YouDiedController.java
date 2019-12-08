import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class YouDiedController  {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button no;
    @FXML
    private Button exit;
    @FXML
    private Button yes;

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

    /**
     * This method asks the user if to go back or exit the game
     */
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
     * This method restarts the game to the last save
     * @param event
     * @throws IOException
     */
    public void handleLevelRestartButton(ActionEvent event) throws IOException{

        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("loadingScreen.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}