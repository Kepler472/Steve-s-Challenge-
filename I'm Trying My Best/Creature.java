
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Creature extends Entity{
	
	protected int x;
	protected int y;
	
	protected String[][] map;
	
	protected Image image;
	/**
	 * super constructor (obviously)
	 * @param position
	 * @param map
	 */
	public Creature (int x, int y, String[][] map) {
		super(x,y);
		this.map = map;

	}
	
	/**
	 * Default move; please override
	 * @param direction
	 */
	public void move() {
		
	}
	
	public boolean[] getAdjancentWalls() {
		
		boolean[] adjancentWalls = new boolean[4];
		
		if(map[getY() - 1][getX()].equals("Wall")) {
			adjancentWalls[0] = true;
		}			
	
		if(map[getY()][getX() + 1].equals("Wall")) {
			adjancentWalls[1] = true;
		}
			
		if(map[getY() + 1][getX()].equals("Wall")) {
			adjancentWalls[2] = true;
		}
			
		if(map[getY()][getX() - 1].equals("Wall")) {
			adjancentWalls[3] = true;
		}
				
		return adjancentWalls;
	}
	
	/**
	 * 
	 */
	public String[]	getAdjancentTiles() {
		
		String[] adjancentTiles = new String[4];
		
		if(getX() - 1 >= 0 && getY() - 1 >= 0) {
			adjancentTiles[0] = map[getY() - 1][getX()];
			
			adjancentTiles[3] = map[getY()][getX() - 1];
			
			adjancentTiles[1] = map[getY()][getX() + 1];
			
			adjancentTiles[2] = map[getY() + 1][getX()];
		}else {
			adjancentTiles[0] = "Caca";
			
			adjancentTiles[1] = map[getY()][getX() + 1];
			
			adjancentTiles[2] = map[getY() + 1][getX()];
			
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
	
	public void draw(GraphicsContext gc) {
		gc.drawImage(image, x * 64, y * 64);
	}
	
}