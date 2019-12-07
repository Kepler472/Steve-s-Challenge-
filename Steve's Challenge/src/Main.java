import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javafx.concurrent.Task;
import javafx.scene.media.AudioClip;

public class Main {
	
	public static void main(String[] args) throws IOException{
        
        LeaderBoard board = new LeaderBoard();
        
        //System.out.println(board.toString());
        System.out.println("This was just a test \n");
        //FileHandler.getFile();
        
        UserProfile user = board.search("Costel");
		int saveSlot = 2;

		File saveFile = new File("C:\\Users\\horat\\eclipse-workspace\\Steve's Challenge\\src\\" + user.getUsername() + saveSlot + ".txt"); 

		if(saveFile.createNewFile()){
            System.out.println(saveFile.getAbsolutePath() + " File Created");
        }else System.out.println("File "+saveFile.getAbsolutePath()+" already exists");
           
          //Write Content
          FileWriter writer = new FileWriter(saveFile);
          writer.write("Felt cute today, might delete later");
          writer.close();
          
          System.out.println(FileHandler.getTokenNumberNeeded("DT15"));
          
          
        /*final Task task = new Task() {
        	@Override
        	protected Object call() throws Exception{
        		int s = AudioClip.INDEFINITE;
        		AudioClip audio = new AudioClip(getClass().getResource("").toExternalForm());
        		audio.setVolume(0.5f);
        		audio.setCycleCount(s);
        		audio.play();
        		return null;
        	}
        };
        Thread thread = new Thread(task);
        thread.start();
        
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
