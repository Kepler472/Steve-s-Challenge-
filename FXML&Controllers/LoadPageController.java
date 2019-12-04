import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoadPageController implements Initializable{
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void backToMainMenu(ActionEvent event) throws IOException{
    	
    	Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
    	Scene scene = new Scene(root);
    	
    	Stage  menu = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	
    	menu.setScene(scene);
    	menu.show();
	}
}
