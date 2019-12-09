import java.io.File;
import java.util.ArrayList;

/**
 * This class represents the user profile
 * @author Horatiu Filip, minor changes made by Bogdan Lazar
 * @version 1.3
 */
public class UserProfile implements Comparable<UserProfile>{

	private String username;
	private String password;

	private int currentScore = 0;
    private int currentTime = 0;
    private int highScore = 0;

    private int[] levelTimes = new int[3];
    private File[] saves = new File[3];

    /**
     * Constructor for file reading.
     * @param username
     * @param password
     * @param levelTimes
     * @param saves
     */
    public UserProfile(String username, String password, int[] levelTimes, File[] saves){
        this.username = username;
        this.password = password;
        setLevelTimesArray(levelTimes);
        this.saves = saves;
    }

    /**
     * Constructor for new users.
     * @param username
     * @param password
     */
    public UserProfile(String username, String password) {
    	this.username = username;
    	this.password = password;
    }

    /**
     * Set the username of the profile.
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Get the profile username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set a password for the profile.
     * @param password
     */
    public void setPassword(String password) {
    	this.password = password;
    }

    /**
     * Get the profile password.
     * @return
     */
    public String getPassword() {
    	return this.password;
    }

    /**
     * Checks if the password given as input correlates
     * with the actual password.
     * @param password
     * @return true is it is, false if not
     */
    public boolean checkPassword(String password) {

    	if(this.password.equals(password)) {
    		return true;
    	}else {
    		return false;
    	}
    }

    /**
     * Set the time it in which the player finished the given level.
     * @param highscore
     * @param level
     */
    public void setLevelTime(int time, int level) {

    	if(level > Launch.getGame().getNumberOfLevels()) {
    		System.out.println("Inexistent level");
    	}else {
    		if(currentTime > levelTimes[level]) {
    			levelTimes[level] = time;
    		}

    	}
    }

    /**
     * Get the time in which the player finished the specific level.
     * @param level
     * @return
     */
    public int getLevelTime(int level) {
    	return levelTimes[level];
    }

    /**
     * Sets times for all levels.
     * Used for file reading
     * @param times
     */
    public void setLevelTimesArray(int[] times) {

    	if(times.length > 3) {
    		System.out.println("Huston, we have a problem. check times length");
    	}else {
        	this.levelTimes = times;
    	}
    }

    /**
     * Get all level times.
     * @return levelTimes. Array of times.
     */
    public int[] getLevelTimesArray() {
    	return levelTimes;
    }

    /**
     * Get the total time the player took to finish all the levels.
     * @return totalTime
     */
    public int getTotalTime() {

    	int totalTime = 0;

    	for(int elem:levelTimes) {
        	totalTime += elem;
        }
    	return totalTime;
    }

    /**
     * Get how many levels the player completed.
     * @return levelCompleted
     */
    public int getLevelCompletion() {
    	int levelCompletion = 0;

    	for(int elem:levelTimes) {
    		if(elem != 0) {
    			levelCompletion++;
    		}
    	}
    	return levelCompletion;
    }

    /**
     * Set the player's total highscore.
     * @param highScore
     */
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    /**
     * Compute the player's highscore.
     * @return score
     */
    public int getHighScore() {

    	int score = 0;

    	for(int i = 0; i < levelTimes.length; i++) {
    		if(levelTimes[i] != 0) {
    			score += (40 / (double)levelTimes[i] + i + 1) * 1000;
    		}
    	}

    	return score;
    }

    /**
     * Get the highscore for a certain level.
     * @param level
     * @return score
     */
    public int getLevelHighScore(int level) {

    	int score = 0;

    	for(int i = 0; i < level + 1; i++) {
    		if(levelTimes[i] != 0) {
    			score += (40 / (double)levelTimes[i] + i + 1) * 1000;
    		}
    	}

    	return score;
    }

    /**
     * Gets the current score that the player has managed to get in the game.
     * @return
     */
    public int getCurrentScore() {
        return currentScore;
    }

    /**
     * Check is the current score is bigger than the total highscore.
     * Changes the highscore to the current score if so.
     */
    public void checkScore(){

        if (getHighScore() < getCurrentScore() ){
            setHighScore(currentScore);
            getHighScore();
        }else {
            setHighScore(highScore);
            getHighScore();
        }
    }

    /**
     * Updates the level times and highscore the player managed to get in the game.
     * After this is done, it is ready to be updated in the leaderboard.
     * @param data
     */
    public void transfereData(UserProfile data) {

    	setHighScore(data.getHighScore());
    	setLevelTimesArray(data.getLevelTimesArray());
    }

    /**
     * Returns level completion as percentage.
     * @return completion percentage
     */
    public double getLevelCompletionPercentage() {

    	double completedLevel = getLevelCompletion()/(double)3;
    	return completedLevel * 100;
    }
    /**
     * Transforms the level times array into a format that can be
     * printed to file.
     * @return str. String containing all the level times.
     */
    public String levelTimesToFile() {

    	String str = "";

    	for(int i = 0; i < levelTimes.length; i++) {
    		if(levelTimes[i] != 0) {
    			str += ", " + levelTimes[i];
    		}
    	}
    	return str;
    }

    /**
     * This method returns the save file of the given save number.
     * @param saveNumber
     * @return
     */
    public File getSave(int saveNumber) {
    	return saves[saveNumber];
    }

    /**
     * This method overrides the save file at saveNumber index with a new file.
     * @param file
     * @param saveNumber
     */
    public void setSave(File file, int saveNumber) {
    	saves[saveNumber] = file;
    }

    /**
     * Set the entire save file array.
     * Used for file loading .
     * @param saves
     */
    public void setSaveArray(File[] saves) {
    	this.saves = saves;
    }

    /**
     * Transforms the save file array into a format that can be
     * printed to file.
     * @return str. String with all the save file names
     */
    public String saveArrayToFile() {

    	String str = "";

    	for(int i = 0; i < saves.length; i++) {
    		if(saves[i] != null) {
    			str += ", " + saves[i].getName();
    		}else {
    			str += ", 0";
    		}
    	}

    	return str;
    }

    /**
     * This method returns a string with all the user data
     * that should appear in the file format.
     * @return String
     */
	public String toFile() {
		return this.username + ", "  + this.password
				+ levelTimesToFile() + saveArrayToFile();
	}

	/**
	 * Overrides the compareTo method so that we will have
	 * a descending priority Queue.
	 */
	@Override
	public int compareTo(UserProfile existing) {

		if (getHighScore() < existing.getHighScore()) {
			return 1;
		}else {
			if(getHighScore() == existing.getHighScore()) {
				return 0;
			}else {
				return -1;
			}
		}
	}

	/**
	 * This method returns a string containing all the player data.
	 * Used for testing only.
	 * @return
	 */
	@Override
    public String toString() {
    	return "username: " + this.username + "\n" + "HighScore: "
    			+ getHighScore() + "\n" + "Total time played: "
    			+ getTotalTime() + "\n" +"Level completion: "
    			+ getLevelCompletionPercentage() + "%\n"
    			+ this.password;
    }

}
