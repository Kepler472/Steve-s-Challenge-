import javafx.concurrent.Task;
import javafx.scene.media.AudioClip;

public class Main {
	
	public static void main(String[] args) {
        
        LeaderBoard board = new LeaderBoard();
        
        
        
        System.out.println(board.toString());
        System.out.println("This was just a test \n");
        FileHandler.getFile();
        
        
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
