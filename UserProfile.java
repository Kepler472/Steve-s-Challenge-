/**
 * 
 * @author Lazar
 *
 */
public class UserProfile implements Comparable<UserProfile>{
    
	private String userName;
	private int highScore = 0;
    private int totalTime = 0;
    private int currentScore = 0;
    private int levelCompletion =0;

    public UserProfile(String userName,int highScore){
        this.userName = userName ;
        this.highScore = highScore;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTotalTime(int totalTime) {
        this.totalTime = totalTime;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public String getUserName() {
        return userName;
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
    
    
	
	@Override
    public String toString() {
    	return "Username: " + this.userName + "\n" + "HighScore: " 
    			+ this.highScore + "\n" + "Total time played: " 
    			+ this.totalTime + "\n" +"Level completion: " + 
    			this.levelCompletion + "%";
    }
	
	public String toFile() {
		return this.userName + ", " + this.highScore + ", " 
				+ this.levelCompletion + ", " + this.totalTime;
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
