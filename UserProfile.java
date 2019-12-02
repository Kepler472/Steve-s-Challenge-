/**
 * 
 * @author Lazar
 *
 */
public class UserProfile implements Comparable<UserProfile>{
    
	private String username;
	private int highScore = 0;
    private int totalTime = 0;
    private int currentScore = 0;
    private int levelCompletion =0;
    private String password;
    
    public UserProfile(String username,int highScore, int levelCompletion, int totalTime){
        this.username = username ;
        this.highScore = highScore;
        this.totalTime = totalTime;
        this.levelCompletion = levelCompletion;
    }
    
    public UserProfile(String username, String password) {
    	this.username = username;
    	this.password = password;
    	
    }

    public void setLevelCompletion(int levelCompletion) {
        this.levelCompletion = levelCompletion;
    }

    public int getLevelCompletion() {
        return levelCompletion;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public String getPassword() {
    	return this.password;
    }
    
    public boolean checkPassword(String password) {
    	if(this.password.equals(password)) {
    		return true;
    	}else {
    		return false;
    	}
    }

    public String getUsername() {
        return username;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public int getHighScore() {
        return highScore;
    }

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
    	setTotalTime(data.getTotalTime());
    	setLevelCompletion(data.getLevelCompletion());
    }
	
	@Override
    public String toString() {
    	return "username: " + this.username + "\n" + "HighScore: " 
    			+ this.highScore + "\n" + "Total time played: " 
    			+ this.totalTime + "\n" +"Level completion: " + 
    			this.levelCompletion + "%\n" + this.password;
    }
	
	public String toFile() {
		return this.username + ", " + this.highScore + ", " 
				+ this.levelCompletion + ", " + this.totalTime + ", " + this.password;
	}
	
	@Override
	public int compareTo(UserProfile existing) {
		if (this.highScore < existing.getHighScore()) {
			return 1;
		}else {
			if(this.highScore == existing.getHighScore()) {
				return 0;
			}else {
				return -1;
			}
		}
	}
}
