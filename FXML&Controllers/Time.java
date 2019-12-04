public class Time {
	private int level;
	private int time;
	private int score;
	
	public Time(int level, int time, int score) {
		this.level = level + 1;
		this.time = time;
		this.score = score;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getScore() {
		return score;
	}
}
