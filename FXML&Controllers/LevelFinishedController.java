import java.io.IOException;
import java.net.URL;
import java.util.PriorityQueue;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LevelFinishedController implements Initializable{

    @FXML
    public Button back;

    @FXML
    public TableView<Time> bestTimesTable;

    @FXML
    public TableColumn<Time, String> username;

    @FXML
    public TableColumn<Time, Integer> time;

    @FXML
    public TableColumn<Time, Integer> score;

    private ObservableList<Time> observableList = FXCollections.observableArrayList();

    /**
     * This method initialize the table view with the values from Leaderboard class
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        LeaderBoard board = new LeaderBoard();
        //int level = Game.getLevel();
        username.setCellValueFactory(new PropertyValueFactory<>("Username"));
        time.setCellValueFactory(new PropertyValueFactory<>("Time"));
        score.setCellValueFactory(new PropertyValueFactory<>("Score"));

        PriorityQueue<UserProfile> queue = new PriorityQueue<UserProfile>(board.getLevelTimes(0));//Game.getLevel());
        System.out.println("got into initialize");

        int i = 0;
        while(!queue.isEmpty() && i < 3) {
            UserProfile user = queue.remove();
            observableList.add(new Time(user.getLevelTime(0),user.getLevelHighScore(0),user.getUsername()));
            i++;
        }

        bestTimesTable.setItems(observableList);
    }

    /**
     * This method switch to the next level after completed
     * @param event
     * @throws IOException
     */

    public void handleNextLevelButton(ActionEvent event) throws IOException{

        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        Launch.getGame().setFileName(Launch.getGame().getNextLevelNumber() + ".txt");

        Parent root = FXMLLoader.load(getClass().getResource("loadingScreen.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
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