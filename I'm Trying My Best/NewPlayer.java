import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class NewPlayer extends Creature{
	
	private int tokenCount;
	
	private int yellowKeyCount;
	private int whiteKeyCount;
	private int blueKeyCount;
	
	private boolean fireBoots;
	private boolean flippers;
	
	//Sprite(for alive and for dead)
	
	public NewPlayer(int x, int y, String[][] map) {
		super(x, y, map);
		clearInventory();
		//Set sprite to alive
	}
	
	public void setSprite(Image img) {
		this.image = img;
	}

	/**
	 * Set the inventory to given inventory
	 * Used for file loading 
	 * @param inventory
	 */
	public void setInventory(int[] inventory){
		
		tokenCount = inventory[0];
		yellowKeyCount = inventory[1];
		whiteKeyCount = inventory[2];
		blueKeyCount = inventory[3];
		
		if(inventory[4] == 1){
			fireBoots = true;
		}else {
			fireBoots = false;
		}
		
		if(inventory[5] == 1){
			flippers = true;
		}else {
			flippers = false;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int[] getInventory(){
		
		int[] inv = new int[6];
		
		inv[0] = tokenCount;
		inv[1] = yellowKeyCount;
		inv[2] = whiteKeyCount;
		inv[3] = blueKeyCount;
		inv[4] = (fireBoots) ? 1 : 0;
		inv[5] = (flippers) ? 1 : 0;
		
		return inv;
	}
	
	public void clearInventory() {
		
		int[] inv = new int[6];
		setInventory(inv);
	}
	
	/**
	 * Move the position of the player in the direction given.
	 * The game update is made in the main game class.
	 * @param keyCode
	 */
	public void move(KeyEvent event) {
		
		KeyCode keyCode = event.getCode();
		switch(keyCode) {
			
			case RIGHT:
				if(!super.getAdjancentWalls()[1]) {
					if(noCollisionWithOtherObjects(1, keyCode)) {
						setX(1);
						System.out.println("RIGHT");
					}
					setX(1);
					System.out.println("RIGHT");
				}
				break;
				
			case LEFT:
				if(!super.getAdjancentWalls()[3]) {
					if(noCollisionWithOtherObjects(3, keyCode)) {
						setX(-1);
						System.out.println("LEFT");
					}
					setX(-1);
					System.out.println("LEFT");
				}
				break;
				
			case UP:
				if(!super.getAdjancentWalls()[0]) {
					if(noCollisionWithOtherObjects(0, keyCode)) {
						setY(-1);
						System.out.println("UP");
					}
					setY(-1);
					System.out.println("UP");
				}
				break;
				
			case DOWN:
				if(!super.getAdjancentWalls()[2]) {
					if(noCollisionWithOtherObjects(2, keyCode)) {
						setY(1);
						System.out.println("DOWN");
					}
					setY(1);
					System.out.println("DOWN");
				}
				break;
				
			default:
				break;
		}
	}
	
	public boolean noCollisionWithOtherObjects(int positionToCheck, KeyCode keyCode) {
		
		boolean shouldMove = false;
		
		if(getAdjancentTiles()[positionToCheck] == null) {
			return false;
		}
		
		if(canOpenDoor(positionToCheck, keyCode) || 
				getAdjancentTiles()[positionToCheck].equals("Floor")) {
			return true;
		}
		
		checkIfTileHasKeyOrToken(positionToCheck, keyCode);
		checkIfTileHasBoots(positionToCheck, keyCode);
		
		return shouldMove;
	}
	
	/**
	 * Checks if there is a door in the direction in 
	 * which the player moves and opens it if he has a key
	 *  
	 * @param positionToCheck
	 * @param keyCode
	 * @return
	 */
	public boolean canOpenDoor(int positionToCheck, KeyCode keyCode) {
		
		boolean hasKey = false;
		
		if(getAdjancentTiles()[positionToCheck].charAt(0) == 'D') {
			
			char doorColour = getAdjancentTiles()[positionToCheck].charAt(1);
			
			switch(doorColour) {
			
				case 'Y':
					if(hasYellowKey()) {
						hasKey = true;
					}
					break;
					
				case 'W':
					if(hasWhiteKey()) {
						hasKey = true; 
					}
					break;
					
				case 'B':
					if(hasBlueKey()) {
						hasKey = true;
					}
					break;
				
				case 'T':
					if(hasEnoughTokens(FileReader.getTokenNumberNeeded(getAdjancentTiles()[positionToCheck]))) {
						hasKey = true;
					}
				
				default:
					break;
			}
		}else if(getAdjancentTiles()[positionToCheck].equals("Floor")) {
			return true;
		}

		if(hasKey) {
			removeObject(keyCode);
		}
		
		return hasKey;
	}
	
	/**
	 * Check if next tile is a key
	 * Picks it up if it is and deletes it from the map
	 * @param positionToCheck
	 * @param keyCode
	 * @return
	 */
	public void checkIfTileHasKeyOrToken(int positionToCheck, KeyCode keyCode) {
		
		
		
		if(getAdjancentTiles()[positionToCheck].charAt(0) == 'K'){
			
			char keyColour = getAdjancentTiles()[positionToCheck].charAt(3);
			
			switch(keyColour) {
			
				case 'Y':
					addYellowKey();
					break;
					
				case 'W':
					addWhiteKey();
					break;
					
				case 'B':
					addBlueKey();
					break;
					
				default:
					break;
			}
			removeObject(keyCode);
		}
		
		if(getAdjancentTiles()[positionToCheck].charAt(0) == 'T' ) {
			addToken();
			removeObject(keyCode);
		}
	}
	
	/**
	 * Checks if the tile has boots on top
	 * If so, add them to the inventory
	 * @param positionToCheck
	 * @param keyCode
	 * @return
	 */
	public void checkIfTileHasBoots(int positionToCheck, KeyCode keyCode) {
		
		String boots = getAdjancentTiles()[positionToCheck];
		
		if(boots.equals("FB")) {
			giveFireBoots();
			removeObject(keyCode);
		}else if(boots.equals("Flippers")) {
			giveFlippers();
			removeObject(keyCode);
		}
	}
	
	public void enteredTeleporter(int positionToCheck, KeyCode keyCode) {
		
	}
	
	/**
	 * Remove the object in the direction the player is moving 
	 * when he is in front of it.
	 * @param keyCode the code of the direction
	 */
	public void removeObject(KeyCode keyCode) {
		
		switch(keyCode) {
		
			case RIGHT: 
			case D:
				map[x + 1][y] = "Floor";
				break;
				
			case LEFT: 
			case A:
				map[x-1][y] = "Floor";
				break;
				
			case UP: 
			case W:
				map[x][y-1] = "Floor";
				break;
				
			case DOWN: 
			case S:
				map[x][y+1] = "Floor";
				break;
				
			default:
				break;
		}
	}
	
	public boolean hasEnoughTokens(int amount) {
		if (tokenCount >= amount) {
			tokenCount -= amount;
			return true;
		} else {
			return false;
		}
	}
	
	public void addToken() {
		tokenCount++;
	}
	
	public boolean hasYellowKey() {
		if (yellowKeyCount > 0) {
			yellowKeyCount--;
			return true;
		} else {
			return false;
		}
	}

	public void addYellowKey() {
		yellowKeyCount++;
	}

	public boolean hasWhiteKey() {
		if (whiteKeyCount > 0) {
			whiteKeyCount--;
			return true;
		} else {
			return false;
		}
	}

	public void addWhiteKey() {
		whiteKeyCount++;
	}

	public boolean hasBlueKey() {
		if (blueKeyCount > 0) {
			blueKeyCount--;
			return true;
		} else {
			return false;
		}
	}

	public void addBlueKey() {
		blueKeyCount++;
	}
	

	public boolean hasFireBoots() {
		return fireBoots;
	}

	public void giveFireBoots() {
		this.fireBoots = true;
	}

	public boolean hasFlippers() {
		return flippers;
	}

	public void giveFlippers() {
		this.flippers = true;
	}

	public String inventoryToFile(){
		
		int[] inv = getInventory();
		String str = "";
		
		for(int i = 0; i < inv.length;i++){
			if(i == inv.length - 1) {
				str += i;
			}else {
				str += i +", ";
			}
		}
		return str;
	}
}

