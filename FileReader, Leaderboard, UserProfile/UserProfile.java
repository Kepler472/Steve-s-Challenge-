import java.io.File;
import java.util.ArrayList;

/**
 * 
 * @author Lazar & Filip
 *
 */
public class UserProfile implements Comparable<UserProfile>{
    
	private final int LEVEL_NUMBER = 3;
	
	private String username;
	private String password;
	
	private int currentScore = 0;
    private int currentTime = 0;
    private int highScore = 0;
    
    private int[] levelTimes = new int[LEVEL_NUMBER];
    private File[] saves = new File[3];
    
    /**
     * Constructor for file reading
     * @param username
     * @param highScore
     * @param levelCompletion
     * @param totalTime
     */
    public UserProfile(String username, String password, int[] levelTimes){
        this.username = username;
        this.password = password;
        setLevelTimesArray(levelTimes);
    }
    
    /**
     * Constructor for new users
     * @param username
     * @param password
     */
    public UserProfile(String username, String password) {
    	this.username = username;
    	this.password = password;
    }
    
    /**
     * 
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * 
     * @return
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * 
     * @param password
     */
    public void setPassword(String password) {
    	this.password = password;
    }
    
    /**
     * 
     * @return
     */
    public String getPassword() {
    	return this.password;
    }
    
    /**
     * 
     * @param password
     * @return
     */
    public boolean checkPassword(String password) {
    	
    	if(this.password.equals(password)) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    /**
     * 
     * @param highscore
     * @param level
     */
    public void setLevelTime(int time, int level) {
    	
    	if(level > LEVEL_NUMBER) {
    		System.out.println("Inexistent level");
    	}else {
    		levelTimes[level] = time;
    	}
    }
    
    /**
     * 
     * @param level
     * @return
     */
    public int getLevelTime(int level) {
    	return levelTimes[level];
    }
    
    /**
     * 
     * @param times
     */
    public void setLevelTimesArray(int[] times) {
    	
    	if(times.length > LEVEL_NUMBER) {
    		System.out.println("Huston, we have a problem. check times length");
    	}else {
        	this.levelTimes = times;
    	}
    }
    
    /**
     * 
     * @return
     */
    public int[] getLevelTimesArray() {
    	return levelTimes;
    }
    
    /**
     * 
     * @return
     */
    public int getTotalTime() {
        
    	int totalTime = 0;
    	
    	for(int elem:levelTimes) {
        	totalTime += elem;
        }
    	return totalTime;
    }
    
    /**
     * 
     * @return
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
     * 
     * @param highScore
     */
    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
    
    /**
     * 
     * @return
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
     * 
     * @param level
     * @return
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
     * 
     * @return
     */
    public int getCurrentScore() {
        return currentScore;
    }
    
    /**
     * 
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
    
    public void transfereData(UserProfile data) {
    	
    	setHighScore(data.getHighScore());
    	setLevelTimesArray(data.getLevelTimesArray());
    }
    
    /**
     * returns level completion as percentage 
     * @return
     */
    public double getLevelCompletionPercentage() {
    	
    	double completedLevel = getLevelCompletion()/(double)LEVEL_NUMBER;
    	return completedLevel * 100;
    }
    /**
     * 
     * @return
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
     * This method returns the file name of the given save number
     * @param saveNumber
     * @return
     */
    public File getSave(int saveNumber) {
    	return saves[saveNumber];
    }
    
    /**
     * This method overrides the save with 
     * @param file
     * @param saveNumber
     */
    public void setSave(File file, int saveNumber) {
    	saves[saveNumber] = file;
    }
	
    /**
     * This method returns a string with all the user data 
     * that should appear in the file format
     * @return String
     */
	public String toFile() {
		return this.username + ", "  + this.password 
				+ levelTimesToFile();
	}
	
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

	@Override
    public String toString() {
    	return "username: " + this.username + "\n" + "HighScore: " 
    			+ getHighScore() + "\n" + "Total time played: " 
    			+ getTotalTime() + "\n" +"Level completion: " 
    			+ getLevelCompletionPercentage() + "%\n" 
    			+ this.password;
    }
}

