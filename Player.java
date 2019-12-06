import javafx.scene.input.KeyCode;

public class Player extends Creature{
	
	private int tokenCount;
	private int brownKeyCount;
	private int whiteKeyCount;
	private int blueKeyCount;
	private boolean fireBoots;
	private boolean flippers;
	//Sprite(for alive and for dead)
	
	public Player(int[] position, String[][] map, String img) {
		super(position, map, img);
		tokenCount = 0;
		brownKeyCount = 0;
		whiteKeyCount = 0;
		blueKeyCount = 0;
		fireBoots = false;
		flippers = false;
		//Set sprite to alive
	}

	public void setInventory(int[] inventory){
		
		tokenCount = inventory[0];
		brownKeyCount = inventory[1];
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
	
	public int[] getInventory(){
		
		int[] inv = new int[6];
		
		inv[0] = tokenCount;
		inv[1] = brownKeyCount;
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
	
	public void move(KeyCode keyCode) {
		
		switch(keyCode) {
			
			case RIGHT:
			case D:
				x++;
				break;
				
			case LEFT:
			case A:
				x--;
				break;
				
			case UP:
			case W:
				y--;
				break;
				
			case DOWN:
			case S:
				y++;
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
	
	public boolean hasBrownKey() {
		if (brownKeyCount > 0) {
			brownKeyCount--;
			return true;
		} else {
			return false;
		}
	}

	public void addBrownKey() {
		brownKeyCount++;
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
