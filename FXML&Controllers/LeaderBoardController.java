import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LeaderBoardController implements Initializable{
	
	@FXML
	private TableView<UserProfile> leaderboardTable;
	@FXML
    public TableColumn<UserProfile, String> username;
	@FXML
	public TableColumn<UserProfile, Integer> highscore;
	
	private ObservableList<UserProfile> observableList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		LeaderBoard board = Game.getLeaderBoard();
		
		username.setCellValueFactory(new PropertyValueFactory<>("Username"));
		highscore.setCellValueFactory(new PropertyValueFactory<>("HighScore"));
		
		ArrayList<UserProfile> aux = board.toArray();
		for(int i = 0; i < aux.size(); i++) {
			observableList.add(aux.get(i));
		}
		
		leaderboardTable.setItems(observableList);
	}
	
	public void backToMainMenu(ActionEvent event) throws IOException{
    	
    	Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
    	Scene scene = new Scene(root);
    	
    	Stage  menu = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	
    	menu.setScene(scene);
    	menu.show();
    }
}
