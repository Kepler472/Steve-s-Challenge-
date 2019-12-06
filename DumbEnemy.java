import javafx.scene.image.Image;

public class DumbEnemy extends Creature {
	
	private Image image;
	private int playerX;
	private int playerY;
	
	public DumbEnemy(int[] position, char[][] map, String img) {

		super(position, map);
		this.map = map;
		image = new Image(img);
	}
	
	public void moveDumbEnemy(char[][] map, int playerX, int playerY) {
		this.playerX = playerX;
		this.playerY = playerY;
	}
	
	public void move(Cell[][] map) {
		if ((playerX - x) < (playerY - y)) {
			if (x > this.playerX) {
				CellType nextType = map[x - 1][y].getType();
				if (nextType.equals(CellType.EMPTY)) {
					x--;
				}
			} else if (x < this.playerX) {
				CellType nextType = map[x + 1][y].getType();
				if (nextType.equals(CellType.EMPTY)) {
					x++;
				}
			}
		} else {
			if (y > this.playerY) {
				CellType nextType = map[x][y - 1].getType();
				if (nextType.equals(CellType.EMPTY)) {
					y--;
				}
			}

			else if (y < this.playerY) {
				CellType nextType = map[x][y + 1].getType();
				if (nextType.equals(CellType.EMPTY)) {
					y++;
				}
			}
		}
	}

}
