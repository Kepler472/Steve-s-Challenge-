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
	 * This function overwrites the profile file with the
	 * modification made in the leaderboard
	 * @param board
	 */
	public static void exportProfiles(LeaderBoard board) {
		
		BufferedWriter writer = null;
		
		try {
			writer = new BufferedWriter(new FileWriter(file,false));
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
	 * This method appends the newly created profile to the file
	 * @param user This is the user to be added
	 */
	public static void appendProfile(UserProfile user) {
		
		BufferedWriter writer = null;
		
		try {
			if(!hasLine(user.toFile())) {
				writer = new BufferedWriter(new FileWriter(file,true));
				writer.newLine();
				writer.write(user.toFile());
				writer.flush();
				writer.close();
			}
		}catch(IOException e) {
			System.out.println("Error mate! (export);");
			System.exit(0);
		}
	}
	
	/**
	 * hasLine checks if the file has a given line
	 */
	public static boolean hasLine(String lineSearched) {
		Scanner in = null;
		
		try {
			in = new Scanner(file);
			
		}catch(FileNotFoundException e) {
			System.out.println("Could not open file.");
			System.exit(0);
		}
		boolean found = false;
		
		while(in.hasNextLine() && found == false) {
			String line = in.nextLine();
			if(lineSearched.equals(line)) {
				found = true;
			}
		}
		
		return found;
	}
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	private static UserProfile readProfile(Scanner input) {
		
		String username = input.next();
		String password = input.next();
		int[] levelTimes = new int[3]; 
		
		for(int i = 0; i < levelTimes.length && input.hasNextInt(); i++) {
			levelTimes[i] = input.nextInt();
		}
		
		UserProfile user = new UserProfile(username,password,levelTimes);
		
		return user;
	}
	
	/**
	 * 
	 * @param in
	 * @param board
	 */
	private static void importProfiles(Scanner in, LeaderBoard board) {
		
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
