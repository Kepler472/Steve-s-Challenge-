/**
* This class stores the the time for a user
* when the level leaderboard is displayed
* @author Horatiu Filip
*/
public class Time {

	private String username = "";
	private int level;
	private int time;
	private int score;

	/**
	* Initializes the time for each level shown in MyProfile
	* @param level
	* @param score
	* @param score
	*/
	public Time(int level, int time, int score) {
		this.level = level + 1;//Level needs to start from 1
		this.time = time;
		this.score = score;
	}

	/**
	 * Initializes the time for each player in LeaderBoard
	 * @param time
	 * @param score
	 * @param username
	 */
	public Time(int time, int score, String username) {
		this.time = time;
		this.score = score;
		this.username = username;
	}

	/**
	 * Get level number
	 * @return level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Get level time
	 * @return time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Get level score
	 * @return score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Get user username
	 * @return username
	 */
	public String getUsername() {
		return username;
	}
}
