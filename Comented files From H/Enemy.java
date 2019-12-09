
public class Enemy  extends Creature{

	protected String type;

	/**
	* Creates an enemy.
	*/
	public Enemy(int x, int y, Game game, String type) {
		super(x, y, game);
		this.type = type;
	}

	/**
	 * Moves the enemy.
	 * Please Override for each type enemy.
	 */
	public void move() {

	}

	/**
	* Returns the type of the enemy.
	* @return
	*/
	public String getType() {
		return type;
	}

	/**
	* This method returns the tiles the enemy cannot walk on.
	* Index meaning: 0 = UP, 1 = RIGHT, 2 = DOWN, 3 = LEFT.
	*/
	public boolean[] getAdjancentNonWalkableTiles() {

		boolean[] adjancentNonWalkableTiles = new boolean[4];

		if(game.getMap()[getY() - 1][getX()].equals("Wall")
				|| game.getMap()[getY() - 1][getX()].equals("Fire")
				|| game.getMap()[getY() - 1][getX()].equals("Water")) {
			adjancentNonWalkableTiles[0] = true;
		}

		if(game.getMap()[getY()][getX() + 1].equals("Wall")
				|| game.getMap()[getY()][getX() + 1].equals("Fire")
				|| game.getMap()[getY()][getX() + 1].equals("Water")) {
			adjancentNonWalkableTiles[1] = true;
		}

		if(game.getMap()[getY() + 1][getX()].equals("Wall")
				|| game.getMap()[getY() + 1][getX()].equals("Fire")
				|| game.getMap()[getY() + 1][getX()].equals("Water")) {
			adjancentNonWalkableTiles[2] = true;
		}

		if(game.getMap()[getY()][getX() - 1].equals("Wall")
				|| game.getMap()[getY()][getX() - 1].equals("Fire")
				|| game.getMap()[getY()][getX() - 1].equals("Water")) {
			adjancentNonWalkableTiles[3] = true;
		}

		return adjancentNonWalkableTiles;
	}
}
