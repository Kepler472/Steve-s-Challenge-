public class Player extends Creature{
	
	private int tokenCount;
	private int brownKeyCount;
	private int whiteKeyCount;
	private int blueKeyCount;
	private boolean fireBoots;
	private boolean flippers;
	//Sprite(for alive and for dead)
	
	public Player(int[] position) {
		super(position);
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
	
	public void getInventory(){
		
		int[] inv = new int[6];
		inv[0] = tokenCount;
		inv[1] = brownKeyCount;
		inv[2] = whiteKeyCount;
		inv[3] = blueKeyCount;
		inv[4] = (fireBoots) ? 1 : 0;
		inv[5] = (flippers) ? 1 : 0;
	}
	
	public void clearInventory() {
		
		int[] inv = new int[6];
		setInventory(inv);
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

	public void giveFireBoots(boolean fireBoots) {
		this.fireBoots = fireBoots;
	}

	public boolean hasFlippers() {
		return flippers;
	}

	public void giveFlippers(boolean flippers) {
		this.flippers = flippers;
	}

	public String inventoryToString(){
		
		String str = "";
		
		return str;
	}
}
