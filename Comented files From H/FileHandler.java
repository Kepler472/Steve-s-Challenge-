import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class reads the profiles and maps.
 * @author Horatiu Filip
 * @author Connor Else
 */
public class FileHandler {

	/*Make sure the file does not have empty lines at the beginning ,
	 *nor in between the lines.
	 */
	private static final File file = new File("profiles.txt");
	private static String[][] map;
	private Game game;

	/**
	 * Initializes the file reader.
	 * @param game The game controller.
	 */
	public FileHandler(Game game) {
		this.game = game;
	}

	/**
	 * Read the map and convert it to a level.
	 * @param levelName The name of the level.
	 */
	public String[][] readFile(String levelName) {
		File levelFile = new File(levelName);
		String[][] map = null;
		try {
			Scanner file = new Scanner(levelFile);
			map = readFile(file);
		} catch (FileNotFoundException e) {
			System.out.println("Level: " + levelName + ", cannot be found.");
		}
		return map;

	}

	/**
	 * Read the map and convert it to a level
	 * @param file The file from which the operation is performed on.
	 */
	public String[][] readFile(Scanner file) {
		int height = file.nextInt();
		int width = file.nextInt();
		String[][] map = new String[height][width];
		file.nextLine();

		file.useDelimiter("\n");
		for (int i = 0; i < height; i ++) {
			String line = file.next();
			for (int j = 0; j < width; j ++) {
				char character = line.charAt(j);
				switch (character) {
				case '*': // Wall
					map[i][j] = "Wall";
					break;
				case 'D': //Door
					map[i][j] = "Door";
					break;
				case 'G': //Goal
					map[i][j] = "Goal";
					break;
				case 'F': //Fire
					map[i][j] = "Fire";
					break;
				case 'W': //Water
					map[i][j] = "Water";
					break;
				case 'P': //Teleporter
					map[i][j] = "Tele";
					break;
				case 'T': //Token
					map[i][j] = "Token";
					break;
				case 'K': //Key
					map[i][j] = "Key";
					break;
				case 'B': //Boots
					map[i][j] = "Boots";
					break;
				default:
					map[i][j] = "Floor";
					break;
				}
			}
		}
		//Add code for extra lines of file
		file.useDelimiter("\\s*,\\s*");
		file.next();
		int y = file.nextInt();
		int x = file.nextInt();
		map[y][x] = "Start";
		while(file.hasNext()) {
			int yPos = -1;
			int xPos = -1;
			String type = "";
			String tile = file.next();
			switch (tile) {
			case "START":
				yPos = file.nextInt();
				xPos = file.nextInt();
				Player player = new Player(xPos, xPos, game);
				game.setPlayer(player);
			case "DOOR":
				type = file.next();
				yPos = file.nextInt();
				xPos = file.nextInt();
				if (type.equals("TOKEN")) {
					int tokensNeeded = file.nextInt();
					map[yPos][xPos] = ("D" + type.charAt(0) + tokensNeeded);
				} else if (type.equals("YELLOW")) {
					map[yPos][xPos] = "DY";
				} else if (type.equals("BLUE")) {
					map[yPos][xPos] = "DB";
				} else if (type.equals("WHITE")) {
					map[yPos][xPos] = "DW";
				} else {
					map[yPos][xPos] = "BROKEN";
				}
				break;
			case "KEY":
				type = file.next();
				yPos = file.nextInt();
				xPos = file.nextInt();
				if (type.equals("BLUE")) {
					map[yPos][xPos] = "KeyBlue";
				} else if (type.equals("WHITE")) {
					map[yPos][xPos] = "KeyWhite";
				} else if (type.equals("YELLOW")) {
					map[yPos][xPos] = "KeyYellow";
				}
				break;
			case "BOOT":
				type = file.next();
				yPos = file.nextInt();
				xPos = file.nextInt();
				if (type.equals("FIRE")) {
					map[yPos][xPos] = "FireBoot";
				} else if (type.equals("WATER")) {
					map[yPos][xPos] = "Flipper";
				}
				break;
			case "TELE":
				yPos = file.nextInt();
				xPos = file.nextInt();
				int yEnd = file.nextInt();
				int xEnd = file.nextInt();
				map[yPos][xPos] = "Tele" + yEnd + xEnd;
			case "ENEMY":
				type = file.next();
				if (type.equals("STRAIGHT")) {
					String direction = file.next();
					yPos = file.nextInt();
					xPos = file.nextInt();
					game.addEnemy(new EnemyStraight(xPos, yPos, direction, game));
				} else if (type.equals("DUMB")) {
					yPos = file.nextInt();
					xPos = file.nextInt();
					game.addEnemy(new EnemyDumb(xPos, yPos, game, game.getPlayer()));
				} else if (type.equals("WALL")) {
					String direction = file.next();
					yPos = file.nextInt();
					xPos = file.nextInt();
					game.addEnemy(new EnemyWall(xPos, yPos, direction, game));
				} else {
					String direction = file.next();
					yPos = file.nextInt();
					xPos = file.nextInt();
				}
				break;
			default:

			}
		}
		toString(map);
		return map;
	}

	/**
	 * Convert map to string .
	 * @param map The map.
	 */
	public static void toString(String[][] map) {
		for(String [] rows : map) {
			for(String cell : rows) {
				System.out.print(cell);
			}
			System.out.println("");
		}
	}

	/**
	 * returns the map of the game.
	 * @return The map
	 */
	public static String[][] getMap(){
		return map;
	}

	/**
	 * Print the name of the handler.
	 */
	public static void getFile() {
		System.out.println(file.getName());
	}

	/**
	 * This function overwrites the profile file with the
	 * modification made in the leaderboard.
	 * @param board The leaderboard.
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
	 * Check if the file has the respective line.
	 * @param lineSearched The string searched for.
	 * @return Whether or not the line is in the file.
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
	 * Get the user profile from the file.
	 * @param input The file from which the profile is read.
	 * @return The profile.
	 */
	private static UserProfile readProfile(Scanner input) {

		String username = input.next();
		String password = input.next();

		int[] levelTimes = new int[3];
		File[] saves = new File[3];

		for(int i = 0; i < levelTimes.length && input.hasNextInt(); i++) {
			levelTimes[i] = input.nextInt();
		}

		int i = 0;
		while(input.hasNext()) {
			saves[i] = new File(input.next());
			i++;
		}

		UserProfile user = new UserProfile(username,password,levelTimes,saves);

		return user;
	}

	/**
	 * Add the profiles from the 'in' to the current leaderboard.
	 * @param in The scanner with the file containing the profiles.
	 * @param board The leaderboard.
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
	 * Add the profiles from the loaded file to the leaderboard.
	 * @param board The current leaderboard.
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

	/**
	 * This method saves the player progress to a given slot
	 * @param map
	 *
	public static void saveProgress(UserProfile user, int saveSlot, String[][] map, Player player) {

		if(user.getSave(saveSlot) == null) {
			File saveFile = new File("C:\\Users\\horat\\eclipse-workspace\\Steve's Challenge\\" + user.getUsername() + saveSlot + ".txt");
		}

		String str = "";

		int n = map.length;
		int m = map[0].length;

		str += m + " " + n + mapToFile(map) + playerPositionToFile(player)
				+"\n"
				+ "\n" + player.inventoryToFile();

		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(user.getSave(saveSlot),false));

			writer.write(str.trim());
			writer.flush();
			writer.close();

		}catch(IOException e) {
			System.out.println("Error mate! (export);");
			System.exit(0);
		}
	}

	/**
	 * Translate the game map into a string for a file.
	 * @param map The map of the game.
	 * @return the map in the form of a string.
	 */
	public static String mapToFile(String[][] map) {

		String str = "\n";

		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[0].length; j++) {

				if(i == map.length - 1 && j == map[0].length - 1) {
					str += map[i][j];
				}else {
					str += map[i][j] + ", ";
				}
			}
			str += "\n";
		}

		return str;
	}

	/**
	 * Get the number of required tokens to open the respective token door.
	 * @param tokenDoor The token door.
	 * @return the number of required tokens.
	 */
	public static int getTokenNumberNeeded(String tokenDoor) {
		System.out.println(tokenDoor);
		int needed = Integer.parseInt(tokenDoor.replaceAll("[\\D]", ""));
		System.out.println(needed);
		return needed;
	}
}
