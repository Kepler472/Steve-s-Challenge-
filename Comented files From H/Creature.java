/**
 * This abstract class represents a creature
 * @author Horatiu Filip
 * @version 1.2
 */
public abstract class Creature extends Entity{

	protected Game game;

	/**
	 * Creates a creature with the x and y coordinates given.
	 * @param position
	 * @param game
	 */
	public Creature (int x, int y, Game game) {
		super(x,y);
		this.game = game;
	}

	/**
	 * Returns an array with whether or not there are walls around the creature.
	 * Index meaning: 0 = UP, 1 = RIGHT, 2 = DOWN, 3 = LEFT.
	 * @return adjancentWalls
	 */
	public boolean[] getAdjancentWalls() {

		boolean[] adjancentWalls = new boolean[4];

		if(game.getMap()[getY() - 1][getX()].equals("Wall")) {
			adjancentWalls[0] = true;
		}

		if(game.getMap()[getY()][getX() + 1].equals("Wall")) {
			adjancentWalls[1] = true;
		}

		if(game.getMap()[getY() + 1][getX()].equals("Wall")) {
			adjancentWalls[2] = true;
		}

		if(game.getMap()[getY()][getX() - 1].equals("Wall")) {
			adjancentWalls[3] = true;
		}

		return adjancentWalls;
	}

	/**
	 * This method returns an array with the adjancent tiles of the creature.
	 * Index meaning: 0 = UP, 1 = RIGHT, 2 = DOWN, 3 = LEFT.
	 * @return adjancentTiles
	 */
	public String[]	getAdjancentTiles() {

		String[] adjancentTiles = new String[4];

		if(getX() - 1 >= 0 && getY() - 1 >= 0) {
			adjancentTiles[0] = game.getMap()[getY() - 1][getX()];

			adjancentTiles[3] = game.getMap()[getY()][getX() - 1];

			adjancentTiles[1] = game.getMap()[getY()][getX() + 1];

			adjancentTiles[2] = game.getMap()[getY() + 1][getX()];
		}else {
			adjancentTiles[0] = "Caca";

			adjancentTiles[1] = game.getMap()[getY()][getX() + 1];

			adjancentTiles[2] = game.getMap()[getY() + 1][getX()];

			adjancentTiles[3] = "Caca";
		}

		return adjancentTiles;
	}

	/**
	 * Checks if there are no walls around the creature
	 * @param array
	 * @return true
	 */
	public boolean areAllFalse(boolean[] array) {

	    for(boolean b : array) {
	    	if(b) {
	    		return false;
	    	}
	    }
	    return true;
	}

}
