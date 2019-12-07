public class Time {
	
	private String username = "";
	private int level;
	private int time;
	private int score;
	
	public Time(int level, int time, int score) {
		this.level = level + 1;
		this.time = time;
		this.score = score;
	}
	
	public Time(int time, int score, String username) {
		this.time = time;
		this.score = score;
		this.username = username;
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
	
	public String getUsername() {
		return username;
	}
}
