import java.io.*;
import java.util.Scanner;

public class FileHandler {

	private static final File file = new File("profiles.txt");
	/*Make sure the file does not have empty lines at the beginning , 
	 *nor in between the lines. 
	 */
	/**
	 * getFile prints the name of the file used
	 */
	public static void getFile() {
		System.out.println(file.getName());
	}
	
	/**
	 * 
	 * @param board
	 */
	public static void exportProfiles(LeaderBoard board) {
		
		BufferedWriter writer = null;
		
		try {
			writer = new BufferedWriter(new FileWriter(file));
			String str = "";
			
			for(UserProfile elem:board.getBoard()) {
				
					str += elem.toFile() + "\n";
			}
			
			writer.write(str.trim());
			writer.flush();
			writer.close();
			
		}catch(IOException e) {
			System.out.println("Error mate! (export);");
			System.exit(0);
		}
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	public static UserProfile readProfile(Scanner input) {
		
		String username = input.next();
		int highscore = input.nextInt();
		
		UserProfile user = new UserProfile(username,highscore);
		
		user.setLevelCompletion(input.nextInt());
		user.setTotalTime(input.nextInt());
		
		return user;
	}
	
	/**
	 * 
	 * @param in
	 * @param board
	 */
	public static void importProfiles(Scanner in, LeaderBoard board) {
		
		Scanner input = null;
		
		while(in.hasNextLine()) {
			String line = in.nextLine();
			input = new Scanner(line);
			input.useDelimiter("\\s*,\\s*");
			
			UserProfile user = readProfile(input);
			board.addUser(user);
		}	
	}
	
	/**
	 * Try and catch for the file given
	 * @param filename name of the file
	 * @param graph graph in which to add the friendships 
	 */
	public static void importProfiles(LeaderBoard board) {
		
		Scanner in = null; 
		
		try {
			in = new Scanner(file);
		}catch(FileNotFoundException e) {
			System.out.println("Could not open file.");
			System.exit(0);
		}
		
		importProfiles(in, board);
		in.close();
	}
}
