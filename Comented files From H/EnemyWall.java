public class EnemyWall extends Enemy {

	private String direction;

	/**
	 * Constructs the enemy
	 * @param x
	 * @param y
	 * @param direction
	 * @param img
	 * @param map
	 */
	public EnemyWall(int x, int y, String direction, Game game) {

		super(x, y, game, "Wall");
		this.direction = direction;
	}

	/**
	 * Get the direction the enemy is traveling in.
	 * @return
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * Set direction.
	 * This should be LEFT, RIGHT, UP or DOWN.
	 * @return
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	/**
	 * Moves the enemy regarding the walls around him.
	 */
	@Override
	public void move() {

		boolean[] adjancentNonWalkableTiles = getAdjancentNonWalkableTiles();

		if(adjancentNonWalkableTiles[0] && !adjancentNonWalkableTiles[1]
				|| adjancentNonWalkableTiles[3] && adjancentNonWalkableTiles[0]
				|| areAllFalse(adjancentNonWalkableTiles) && direction.equals("DOWN")) {
					setX(1);
					direction = "RIGHT";

		}else if(adjancentNonWalkableTiles[0] && adjancentNonWalkableTiles[1]
				|| adjancentNonWalkableTiles[1] && !adjancentNonWalkableTiles[2]
				|| areAllFalse(adjancentNonWalkableTiles) && direction.equals("LEFT")) {
			  		setY(1);
			  		direction = "DOWN";

		}else if(adjancentNonWalkableTiles[1] && adjancentNonWalkableTiles[2]
				|| adjancentNonWalkableTiles[2] && !adjancentNonWalkableTiles[3]
				|| areAllFalse(adjancentNonWalkableTiles) && direction.equals("UP")) {
					setX(-1);
					direction = "LEFT";

		}else if(adjancentNonWalkableTiles[2] && adjancentNonWalkableTiles[3]
				|| adjancentNonWalkableTiles[3] && !adjancentNonWalkableTiles[0]
				|| areAllFalse(adjancentNonWalkableTiles) && direction.equals("RIGHT")) {
					setY(-1);
					direction = "UP";
		}
	}

}
