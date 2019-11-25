import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main {
	
	
	

	public static void main(String[] args) {
        
        LeaderBoard board = new LeaderBoard();
        UserProfile profile = new UserProfile("Gregorel",479);
        
        board.addUser(profile);
        
        System.out.println(board.toString());
        System.out.println("This was just a test \n");
        FileHandler.getFile();
        
        /**

        HashMap<String, String> userNameAndPassword = new HashMap<String, String>();

        System.out.println("Enter your username: ");
        Scanner us = new Scanner(System.in);
        String username  = us.nextLine();

        System.out.println("Enter your password: ");
        String password = us.nextLine();

        userNameAndPassword.put(username,password);
        System.out.println(userNameAndPassword);
*/
    }
}
