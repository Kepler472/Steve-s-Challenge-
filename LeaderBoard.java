import java.util.*;

public class LeaderBoard {

	PriorityQueue<UserProfile> board;
	
	public LeaderBoard() {
		
		board = new PriorityQueue<UserProfile>();
		FileHandler.importProfiles(this);
	}
	
	/**
	 * 
	 * @return
	 */
	public PriorityQueue<UserProfile> getBoard(){
		return this.board;
	}
	
	/**
	 * 
	 * @param user
	 */
	public void addUser(UserProfile user) {
		
		if(user == null) {
			System.out.println("No user given...");
		}else {
			UserProfile searchedUser = search(user.getUserName());
				
			if(searchedUser == null) {
				board.add(user);
			}else {
				if(compareScore(user, searchedUser) == user) {
					board.remove(searchedUser);
					board.add(user);
				}
			}
			
			FileHandler.exportProfiles(this);
		}
		
	}
	
	/**
	 * 
	 * @param newUser
	 * @param user
	 * @return
	 */
	public UserProfile compareScore(UserProfile newUser, UserProfile user) {
		
		if(newUser.getHighScore() > user.getHighScore())
			return newUser;
		else {
			return user;
		}
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public UserProfile search(String name) {
		
		UserProfile[] profiles = board.toArray(new UserProfile[0]);
		
		for(UserProfile elem:profiles) {
			if(name.equals(elem.getUserName())) {  //schema
				return elem;
			}
		}
		return null;
	}
	
	public boolean hasProfile(String name) {
		if(search(name)!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 
	 */
	public String toString() {
		PriorityQueue<UserProfile> aux = board;
		String users = "";
		while(!aux.isEmpty()) {
			System.out.println(aux.remove());
			System.out.println();
		}
		return users;
	}
	
}


