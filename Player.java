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

	public void clearInventory() {
		tokenCount = 0;
		brownKeyCount = 0;
		whiteKeyCount = 0;
		blueKeyCount = 0;
		giveFireBoots(false);
		giveFlippers(false);
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

	
	


}
