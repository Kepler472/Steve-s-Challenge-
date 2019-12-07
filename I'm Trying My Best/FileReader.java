import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;



public class FileReader {
	public static String[][] readFile(String levelName) {
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
	
	public static String[][] readFile(Scanner file) {
		int height = file.nextInt();
		int width = file.nextInt();
		String[][] map = new String[height][width];
		file.nextLine();
		file.useDelimiter("\\s*,\\s*");
		for (int i = 0; i < height; i ++) {
			for (int j = 0; j < width; j ++) {
				String type = file.next();
				map[i][j] = type;
			}
			file.nextLine();
		}
		//Add code for extra lines of file
		file.useDelimiter("\\s*,\\s*");
		System.out.println(file.next());
		System.out.println("2");
		int x = file.nextInt();
		int y = file.nextInt();
		NewPlayer player = new NewPlayer(x, y, map);
		toString(map);
		return map;
	}
	
	public static void toString(String[][] map) {
		for(String [] rows : map) {
			for(String cell : rows) {
				System.out.print(cell);
			}
			System.out.println("");
		}
	}
	
	public static int getTokenNumberNeeded(String tokenDoor) {
		return Integer.parseInt(tokenDoor.replaceAll("[\\D]", ""));
	}

}


