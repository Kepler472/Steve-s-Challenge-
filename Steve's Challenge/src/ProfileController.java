import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ProfileController implements Initializable{
	
	@FXML
	public Button back;
	
	@FXML
	public Label username;
	
	@FXML
	public Label highscore;
	
	@FXML
	public TableView<Time> levelTimesTable;
	
	@FXML
    public TableColumn<Time, Integer> level;
	
	@FXML
	public TableColumn<Time, Integer> time;
	
	@FXML
	public TableColumn<Time, Integer> score;
	
	private ObservableList<Time> observableList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		UserProfile user = LoginController.getUserProfile();
		
		level.setCellValueFactory(new PropertyValueFactory<>("Level"));
		time.setCellValueFactory(new PropertyValueFactory<>("Time"));
		score.setCellValueFactory(new PropertyValueFactory<>("Score"));

		username.setText(LoginController.getUserProfile().getUsername());
		highscore.setText(String.valueOf(LoginController.getUserProfile().getHighScore()));
		
		int[] list = user.getLevelTimesArray();
		
		for(int i = 0; i < list.length; i++) {
			if(list[i] != 0) {
				observableList.add(new Time(i, list[i], user.getLevelHighScore(i)));
			}
		}
		
		levelTimesTable.setItems(observableList);
		
	}
	
	public void backToMainMenu(ActionEvent event) throws IOException{
    	
    	Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
    	Scene scene = new Scene(root);
    	
    	Stage  menu = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	
    	menu.setScene(scene);
    	menu.show();
    }
}
