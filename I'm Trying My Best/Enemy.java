
public class Enemy  extends Creature{

	public Enemy(int x, int y, Game game) {
		super(x, y, game);
	}

	/**
	 * Moves the enemy.
	 * Please Override for each type enemy
	 */
	public void move() {
		
	}
	
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
