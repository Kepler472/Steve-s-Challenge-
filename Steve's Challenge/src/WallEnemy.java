import javafx.scene.image.Image;

public class WallEnemy extends Creature {

	private String direction;
	
	/**
	 * Constructs the enemy
	 * @param x
	 * @param y
	 * @param direction
	 * @param img
	 * @param map
	 */
	public WallEnemy(int x, int y, String direction, Game game) {
		
		super(x, y, game);
		this.direction = direction;
	}
	
	/**
	 * Get the direction the enemy is traveling in
	 * @return
	 */
	public String getDirection() {
		return direction;
	}
	
	/**
	 * Set direction
	 * This should be LEFT RIGHT, UP or DOWN
	 * @return
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	/**
	 * Moves the enemy regarding the walls around him
	 */
	@Override
	public void move() {
		
		boolean[] adjancentWalls = getAdjancentWalls();
		
		if(adjancentWalls[0] && !adjancentWalls[1] || adjancentWalls[3] && adjancentWalls[0] 
				|| areAllFalse(adjancentWalls) && direction == "DOWN") {
					x++;
					direction = "RIGHT";
					
		}else if(adjancentWalls[0] && adjancentWalls[1] || adjancentWalls[1] && !adjancentWalls[2] 
				|| areAllFalse(adjancentWalls) && direction == "LEFT") {
			  		y++;
			  		direction = "DOWN";
			  		
		}else if(adjancentWalls[1] && adjancentWalls[2] || adjancentWalls[2] && !adjancentWalls[3] 
				|| areAllFalse(adjancentWalls) && direction == "UP") {
					x--;
					direction = "LEFT";
					
		}else if(adjancentWalls[2] && adjancentWalls[3] || adjancentWalls[3] && !adjancentWalls[0] 
				|| areAllFalse(adjancentWalls) && direction == "RIGHT") {
					y--;
					direction = "UP";
		}
	}
	
}
