import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * This class represents the player entity.
 * @author Connor Else, heavily modified by Horatiu Filip
 * @version 1.4
 */
public class Player extends Creature{

	//Count = counter
	private int tokenCount;

	private int yellowKeyCount;
	private int whiteKeyCount;
	private int blueKeyCount;

	private boolean fireBoots;
	private boolean flippers;

	private boolean alive = true; //the life state of the player

	/**
	* Initializes the player with the starting coordinates x and y.
	* @param x. X coordinate
	* @param y. Y coordinate
	*/
	public Player(int x, int y, Game game) {
		super(x, y, game);
		clearInventory();
	}

	/**
	 * Set the inventory to given inventory.
	 * Used for file loading .
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
	 * Gets the inventory in the form of an integer array.
	 * @return int[] inventory
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

	/**
	 * Clears the inventory.
	 * Aka sets every counter to 0 and boots to to false.
	 */
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
			case D:
				if(getAdjancentTiles()[1].contains("D") && canOpenDoor(1, keyCode) ) {
					setX(1);
				} else if(!getAdjancentWalls()[1] && !getAdjancentTiles()[1].contains("D")) {
					if(noCollisionWithOtherObjects(1, keyCode)) {
						setX(1);
						System.out.println("RIGHT");
					}
				}
				break;

			case LEFT:
			case A:
				if(getAdjancentTiles()[3].contains("D") && canOpenDoor(3, keyCode) ) {
					setX(-1);
				} else if(!getAdjancentWalls()[3] && !getAdjancentTiles()[3].contains("D")) {
					if(noCollisionWithOtherObjects(3, keyCode)) {
						setX(-1);
						System.out.println("LEFT");
					}
				}
				break;

			case UP:
			case W:
				if (getAdjancentTiles()[0].contains("Tele")){
					int yEnd = (int) getAdjancentTiles()[0].charAt(4);
					int xEnd = (int) getAdjancentTiles()[0].charAt(5);
					int yDes = getY() - yEnd - 1;
					int xDes =getX() - xEnd;
					System.out.println(yDes + "," + xDes);
					setY(12-y);
					setX(13-x);
				} else if(getAdjancentTiles()[0].contains("D") && canOpenDoor(0, keyCode) ) {
					setY(-1);
				} else if(!getAdjancentWalls()[0] && !getAdjancentTiles()[0].contains("D")) {
					if(noCollisionWithOtherObjects(0, keyCode)) {
							setY(-1);
							System.out.println("UP");
						}
				}

				break;

			case DOWN:
			case S:
				if(getAdjancentTiles()[2].contains("D") && canOpenDoor(2, keyCode) ) {
					setY(1);
				} else if(!getAdjancentWalls()[2] && !getAdjancentTiles()[2].contains("D")) {
					if(noCollisionWithOtherObjects(2, keyCode)) {
						setY(1);
						System.out.println("UP");
					}
				}
				break;

			default:
				break;
		}
	}

	/**
	 * Handles collision with objects on the map other than walls.
	 * @param positionToCheck
	 * @param keyCode
	 * @return boolean. Returns true if the player can move.
	 */
	public boolean noCollisionWithOtherObjects(int positionToCheck, KeyCode keyCode) {

		if(getAdjancentTiles()[positionToCheck] == null) {
			return false;
		}

		if(canOpenDoor(positionToCheck, keyCode) || checkIfTileHasKeyOrToken(positionToCheck, keyCode)
				|| checkIfTileHasBoots(positionToCheck, keyCode) || canWalkOnFireOrWater(positionToCheck, keyCode)) {
			return true;
		}

		return false;
	}

	/**
	 * Checks if there is a door in the direction in
	 * which the player moves and opens it if he has a key.
	 *
	 * @param positionToCheck
	 * @param keyCode
	 * @return boolean
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
					System.out.println(getTokens());
					if(hasEnoughTokens(FileHandler.getTokenNumberNeeded(getAdjancentTiles()[positionToCheck]))) {
						hasKey = true;
					}

				default:
					break;
			}
		}else if(getAdjancentTiles()[positionToCheck].equals("Floor") || getAdjancentTiles()[positionToCheck].equals("Goal")) {
			return true;
		}

		if(hasKey) {
			removeObject(keyCode);
		}

		return hasKey;
	}

	/**
	 * Check if next tile is a key.
	 * If yes, adds it to the inventory and deletes it from the map.
	 * @param positionToCheck
	 * @param keyCode
	 * @return boolean
	 */
	public boolean checkIfTileHasKeyOrToken(int positionToCheck, KeyCode keyCode) {

		boolean taken = false;
		//Check if the first letter of the tile for Key
		if(getAdjancentTiles()[positionToCheck].charAt(0) == 'K'){
			//Get the key colour initial
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

			taken = true;
			removeObject(keyCode);
		}

		if(getAdjancentTiles()[positionToCheck].equals("Token")) {

			addToken();
			taken = true;
			removeObject(keyCode);
		}

		return taken;
	}

	/**
	 * Checks if the tile has boots on top.
	 * If so, add them to the inventory.
	 * @param positionToCheck
	 * @param keyCode
	 * @return boolean
	 */
	public boolean checkIfTileHasBoots(int positionToCheck, KeyCode keyCode) {

		String boots = getAdjancentTiles()[positionToCheck];

		if(boots.equals("FireBoot")) {
			giveFireBoots();
			removeObject(keyCode);
			return true;
		}else if(boots.equals("Flipper")) {
			giveFlippers();
			removeObject(keyCode);
			return true;
		}else if(boots.equals("Boots")) {
			giveFireBoots();
			giveFlippers();
			removeObject(keyCode);
			return true;
		}

		return false;
	}

	/**
	 * Checks if the player has the specific boots to walk on the special tile.
	 * If not, change his life state to false(dead).
	 * @param positionToCheck
	 * @param keyCode
	 * @return boolean
	 */
	public boolean canWalkOnFireOrWater(int positionToCheck, KeyCode keyCode){

		if(getAdjancentTiles()[positionToCheck].equals("Water")) {
			if(!hasFlippers()) {
				alive = false;
			}
		}else if(getAdjancentTiles()[positionToCheck].equals("Fire")) {
			if(!hasFireBoots()) {
				alive = false;
			}
		}
		return true;
	}


	/**
	 * Returns true if the player is on the same tile with the goal.
	 * @param positionToCheck
	 * @return boolean
	 */
	public boolean hasReachedGoal() {

		if(game.getMap()[getY()][getX()].equals("Goal")) {
			return true;
		}

		return false;
	}

	/**
	* Handles collision with enemies.
	* If the player is on the same tile as an enemy, change his life state to false.
	*/
	public void handleEnemyCollision() {
		for(Enemy enemy:game.getEnemyList()) {
			if(getX() == enemy.getX() && getY() == enemy.getY()) {
				alive = false;
			}
		}
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
				game.getMap()[getY()][getX()+ 1] = "Floor";
				break;

			case LEFT:
			case A:
				game.getMap()[getY()][getX()-1] = "Floor";
				break;

			case UP:
			case W:
				game.getMap()[getY()-1][getX()] = "Floor";
				break;

			case DOWN:
			case S:
				game.getMap()[getY()+1][getX()] = "Floor";
				break;

			default:
				break;
		}
	}

	/**
	 * Checks if the player has enough tokens to go to the goal.
	 * Delete the amount from his inventory .
	 * @param amount Amount needed for the token door.
	 * @return
	 */
	public boolean hasEnoughTokens(int amount) {
		if (tokenCount >= amount) {
			tokenCount -= amount;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Adds a token to the token counter .
	 */
	public void addToken() {
		tokenCount++;
	}

	/**
	* Gets the number of tokens in his inventory.
	*/
	public int getTokens() {
		return tokenCount;
	}

	/**
	 * Checks if the player has a key to open the yellow door.
	 * Deletes a yellow key from his inventory.
	 * @return
	 */
	public boolean hasYellowKey() {
		if (yellowKeyCount > 0) {
			yellowKeyCount--;
			return true;
		} else {
			return false;
		}
	}

	/**
	* Adds a yellow key to the yellow key counter.
	*/
	public void addYellowKey() {
		yellowKeyCount++;
	}

	/**
	 * Checks if the player has a key to open the white door.
	 * Deletes a white key from his inventory.
	 * @return
	 */
	public boolean hasWhiteKey() {
		if (whiteKeyCount > 0) {
			whiteKeyCount--;
			return true;
		} else {
			return false;
		}
	}

	/**
	* Adds a white key to the white key counter.
	*/
	public void addWhiteKey() {
		whiteKeyCount++;
	}

	/**
	 * Checks if the player has a key to open the blue door.
	 * Deletes a blue key from his inventory.
	 * @return
	 */
	public boolean hasBlueKey() {
		if (blueKeyCount > 0) {
			blueKeyCount--;
			return true;
		} else {
			return false;
		}
	}

	/**
	* Adds a blue key to the blue key counter.
	*/
	public void addBlueKey() {
		blueKeyCount++;
	}

	/**
	* Checks if the player has fire boots.
	*/
	public boolean hasFireBoots() {
		return fireBoots;
	}

	/**
	*	Add fire boots to the player's inventory.
	*/
	public void giveFireBoots() {
		this.fireBoots = true;
	}

	/**
	* Checks if the player has flippers.
	*/
	public boolean hasFlippers() {
		return flippers;
	}

	/**
	*	Add flippers to the player's inventory.
	*/
	public void giveFlippers() {
		this.flippers = true;
	}

	/**
	* Returns the life state of the player
	*/
	public boolean getLifeState() {
		return alive;
	}

	/**
	* Transforms the inventory into a format that can be
	* printed to file.
	* @return str. String holding the inventory
	*/
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
