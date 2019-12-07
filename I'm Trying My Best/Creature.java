
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
	
	/**
	 * This method returns an array with whether or not there are walls around
	 * Indexes: 0 = UP, 1 = RIGHT, 2 = DOWN, 3= LEFT 
	 * @return 
	 */
	public boolean[] getAdjancentWalls() {
		
		boolean[] adjancentWalls = new boolean[4];
		
		if((x - 1) >= 0 && (y-1) >= 0) {
			if(map[x][y - 1].equals("Wall")) {
				adjancentWalls[0] = true;
			}
			
			if(map[x - 1][y].equals("Wall")) {
				adjancentWalls[3] = true;
			}
			
			if(map[x + 1][y].equals("Wall")) {
				adjancentWalls[1] = true;
			}
			
			if(map[x][y + 1].equals("Wall")) {
				adjancentWalls[2] = true;
			}
		}
		
		return adjancentWalls;
	}
	
	/**
	 * 
	 */
	public String[]	getAdjancentTiles() {
		String[] adjancentTiles = new String[4];
		if((x - 1) >= 0 && (y-1) >= 0) {
			adjancentTiles[0] = map[x][y - 1];
			
			adjancentTiles[3] = map[x - 1][y];
			
			adjancentTiles[1] = map[x + 1][y];
			
			adjancentTiles[2] = map[x][y + 1];
		}else {
			adjancentTiles[0] = " ";
			
			adjancentTiles[3] = " ";
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