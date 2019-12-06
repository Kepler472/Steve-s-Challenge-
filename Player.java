import java.awt.Color;
import java.awt.Graphics;

public class Player extends Creature{
	
	private int tokenCount;
	private int brownKeyCount;
	private int whiteKeyCount;
	private int blueKeyCount;
	private boolean fireBoots;
	private boolean flippers;
	private Game game;
	
	private boolean hasMoved;
	//Sprite(for alive and for dead)
	
	public Player(Game game, float x, float y) {
		super(x, y);
		tokenCount = 0;
		brownKeyCount = 0;
		whiteKeyCount = 0;
		blueKeyCount = 0;
		fireBoots = false;
		flippers = false;
		this.game = game;
		hasMoved = false;
		//Set sprite to alive
	}
	
	public boolean hasMoved() {
		return hasMoved;
	}
	
	public void setMoved(boolean moved) {
		hasMoved = moved;
	}

	@Override
	public void update() {
//		if(game.getKeyManager().up) {
//			this.y -= 64;
//			setMoved(true);
//		}else if(game.getKeyManager().down) {
//			y += 64;
//			setMoved(true);
//		}else if(game.getKeyManager().left) {
//			this.x -= 64;
//			setMoved(true);
//		}else if(game.getKeyManager().right) {
//			this.x += 64;
//			setMoved(true);
//		} else { 
//			setMoved(false);
//		}
		
		
	}

	@Override
	public void render(Graphics g) {
		System.out.println("A");
		//draw player, use an assets class for a sprite sheet.
		g.setColor(Color.ORANGE);
		g.fillOval((int) this.x, (int) this.y, 64, 64);
		
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

	public int getX() {
		return (int) x;
	}
	
	public int getY() {
		return (int) y;
	}
	
	


}
