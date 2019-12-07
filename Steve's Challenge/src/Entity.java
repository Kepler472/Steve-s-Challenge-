public abstract class Entity {
	
	protected int x, y;
	
	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
	}

	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int xDir) {
		this.x += xDir;
	}
	
	public void setY(int yDir) {
		this.y += yDir;
	}
}
