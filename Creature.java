
public abstract class Creature {
	
	protected int x;
	protected int y;
	protected char[][] map;
	
	/**
	 * super constructor (obviously)
	 * @param position
	 * @param map
	 */
	public Creature (int[] position, char[][] map) {
		setPosition(position);
		this.map = map;
	}
	
	/**
	 * Sets the position of the creature 
	 * @param position
	 */
	public void setPosition(int[] position) {
		this.x = position[0];
		this.y = position[1];
	}
	
	/**
	 * Returns the x coordinate
	 * @return
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Sets x coordinate
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Returns y coordinate
	 * @return
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * gets y coordinate
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * gets the position as an array of two ints
	 * @return
	 */
	public int[] getPosition() {
		return new int[]{x,y};
	}
	
	/**
	 * Default move; please override
	 * @param direction
	 */
	public void move() {
		
	}
	
	/**
	 * This method returns an array with whether or not there are walls around
	 * Indexes: 0 = UP, 1 = RIGHT, 2 = DOWN, 3= LEFT 
	 * @return 
	 */
	public boolean[] getAdjancentWalls() {
		
		boolean[] adjancentWalls = new boolean[4];
		
		if(map[x][y - 1] == '*') {
			adjancentWalls[0] = true;
		}
		
		if(map[x + 1][y] == '*') {
			adjancentWalls[1] = true;
		}
		
		if(map[x][y + 1] == '*') {
			adjancentWalls[2] = true;
		}
		
		if(map[x - 1][y] == '*') {
			adjancentWalls[3] = true;
		}
		
		return adjancentWalls;
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