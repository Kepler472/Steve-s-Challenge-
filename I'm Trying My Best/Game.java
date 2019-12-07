import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Game extends Application {
	// The dimensions of the window
	private static final int WINDOW_WIDTH = 1280;
	private static final int WINDOW_HEIGHT = 1000;

	// The dimensions of the canvas
	private static final int CANVAS_WIDTH = 1280;
	private static final int CANVAS_HEIGHT = 1000;

	// The size of each cell
	private static int TILE_SIZE = 64;
	private static Stage primaryStage;
	
	private static LeaderBoard leaderBoard = new LeaderBoard();
	
	private Canvas canvas;
	
	Image floor;
	Image wall;
	Image tokenDoor;
	Image goal;
	Image fire;
	Image water;
	Image tele;
	Image token;
	Image yellowKey;
	Image blueKey;
	Image whiteKey;
	Image boot;
	Image yellowDoor;
	Image blueDoor;
	Image whiteDoor;
	Image playerSprite;
	
	private String[][] map;
	
	private Player player;
	
	@Override
	public void start(Stage primaryStage) {
		BorderPane root = new BorderPane();
		
		canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		root.setCenter(canvas);
		
		Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		floor = new Image("Floor.png");
		wall = new Image("Wall.png");
		tokenDoor = new Image("TokenDoor.png");
		goal = new Image("Goal.png");
		fire = new Image("Fire.png");
		water = new Image("Water.png");
		tele = new Image("Tele.png");
		token = new Image("Token.png");
		yellowKey = new Image("KeyYellow.png");
		blueKey = new Image("KeyBlue.png");
		whiteKey = new Image("KeyWhite.png");
		boot = new Image("Boot.png");
		yellowDoor = new Image("KeyDoorYellow.png");
		blueDoor = new Image("KeyDoorBlue.png");
		whiteDoor = new Image("KeyDoorWhite.png");
		playerSprite = new Image("Player.png");
		
		map = FileHandler.readFile("levelTest.txt");
		
		player = new Player(7, 9, map);
		player.setSprite(playerSprite);
		
		scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> processKeyEvent(event));
		
		drawGame();
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public void changeToLevelCompletedWindow(KeyEvent event) {
	    //should be a play game function here
		try {
			Parent root = FXMLLoader.load(getClass().getResource("levelCompleted.fxml"));
			Scene scene = new Scene(root);
			
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			
			stage.setScene(scene);
		    stage.show();
		    System.out.println("levelCompleted page loaded");
		    
		}catch(IOException e) {
			System.out.println("Couldn't open file boss");
			System.exit(0);
		}	
	}
		

	private void processKeyEvent(KeyEvent event) {
		
		player.move(event);
		
		drawGame();
		
		if(player.hasReachedGoal()) {
			changeToLevelCompletedWindow(event);
		}
		
		event.consume();
	}
	
	public void drawGame() {
		// Get the Graphic Context of the canvas. This is what we draw on.
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		// Clear canvas
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		int i = 0;
		int j = 0;
		for (String[] rows : map) {
			for (String cell : rows) {
				if (cell.equals("Wall")) {
					gc.drawImage(wall, i * TILE_SIZE, j * TILE_SIZE);
				} else if (cell.contains("DT")) {
					gc.drawImage(tokenDoor, i * TILE_SIZE, j * TILE_SIZE);
				}else if (cell.equals("DY")) {
					gc.drawImage(yellowDoor, i * TILE_SIZE, j * TILE_SIZE);
				}else if (cell.equals("DB")) {
					gc.drawImage(blueDoor, i * TILE_SIZE, j * TILE_SIZE);
				}else if (cell.equals("DW")) {
					gc.drawImage(whiteDoor, i * TILE_SIZE, j * TILE_SIZE);
				} else if (cell.equals("Goal")) {
					gc.drawImage(goal, i * TILE_SIZE, j * TILE_SIZE);
				} else if (cell.equals("Fire")) {
					gc.drawImage(fire, i * TILE_SIZE, j * TILE_SIZE);
				} else if (cell.equals("Water")) {
					gc.drawImage(water, i * TILE_SIZE, j * TILE_SIZE);
				} else if (cell.equals("Tele")) {
					gc.drawImage(tele, i * TILE_SIZE, j * TILE_SIZE);
				} else if (cell.equals("Token")) {
					gc.drawImage(floor, i * TILE_SIZE, j * TILE_SIZE);
					gc.drawImage(token, i * TILE_SIZE, j * TILE_SIZE);
				} else if (cell.equals("KeyYellow")) {
					gc.drawImage(floor, i * TILE_SIZE, j * TILE_SIZE);
					gc.drawImage(yellowKey, i * TILE_SIZE, j * TILE_SIZE);
				}else if (cell.equals("KeyWhite")) {
					gc.drawImage(floor, i * TILE_SIZE, j * TILE_SIZE);
					gc.drawImage(whiteKey, i * TILE_SIZE, j * TILE_SIZE);
				}else if (cell.equals("KeyBlue")) {
					gc.drawImage(floor, i * TILE_SIZE, j * TILE_SIZE);
					gc.drawImage(blueKey, i * TILE_SIZE, j * TILE_SIZE);
				} else if (cell.equals("Boots")) {
					gc.drawImage(floor, i * TILE_SIZE, j * TILE_SIZE);
					gc.drawImage(boot, i * TILE_SIZE, j * TILE_SIZE);
				} else{
					gc.drawImage(floor, i * TILE_SIZE, j * TILE_SIZE);
				}
					i++;	
		}
			j++;
			i = 0;
		}
		
		// Draw player at current location
		gc.drawImage(playerSprite, player.getX() * TILE_SIZE, player.getY() * TILE_SIZE);			
	}
	
	public static LeaderBoard getLeaderBoard() {
		return leaderBoard;
	}
	
	 
	public static void main(String[] args) {
		launch(args);
	}
	
	
}