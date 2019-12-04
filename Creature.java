
public abstract class Creature {
	protected int[] position;
	
	public Creature (int[] position) {
		this.position = position;
	}
	
	public void move(int[] direction) {
		position[0] += direction[0];
		position[1] += direction[1];
	}
	
	
}
