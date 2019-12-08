public class EnemyDumb extends Enemy {

	private String direction;
	int xPlayerPos;
	int yPlayerPos;
	
	/**
	 * Constructs the enemy
	 * @param x
	 * @param y
	 * @param direction
	 * @param img
	 * @param map
	 */
	public EnemyDumb(int x, int y, String direction, Game game, int xPlayerPos, int yPlayerPos) {
		super(x, y, game);
		this.direction = direction;
		this.xPlayerPos = xPlayerPos;
		this.yPlayerPos = yPlayerPos;
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
	
		if((xPlayerPos - x) > (yPlayerPos -y) && (xPlayerPos - x) > 0){
			setX(1);
			direction = "RIGHT";
		} else if((xPlayerPos - x) > (yPlayerPos -y) && (xPlayerPos - x) < 0){
			setX(-1);
			direction = "LEFT";
		} else if((xPlayerPos - x) < (yPlayerPos -y) && (xPlayerPos - x) > 0) {
			setY(1);
	  		direction = "DOWN";
		}else if((xPlayerPos - x) < (yPlayerPos -y) && (xPlayerPos - x) < 0) {
			setY(-1);
			direction = "UP";
		}

	}
	
}