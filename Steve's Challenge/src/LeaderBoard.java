import java.util.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LeaderBoard {

	private PriorityQueue<UserProfile> board;
	
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
			UserProfile searchedUser = search(user.getUsername());
				
			if(searchedUser == null) {
				board.add(user);
				FileHandler.appendProfile(user);
			}else {
				update(user);
			}
		}		
	}
	
	public PriorityQueue<UserProfile> getLevelTimes(int level){
		
		PriorityQueue<UserProfile> highScoreList = new PriorityQueue<UserProfile>();
		PriorityQueue<UserProfile> aux = new PriorityQueue<>(board);
		UserProfile user = null;
		
		while(!aux.isEmpty()) {
			user = aux.remove();
			if(user.getLevelCompletion() >= level) {
				highScoreList.add(user);
			}
		}
		return highScoreList;
	}
	
	/**
	 * 
	 * @param user
	 */
	public void update(UserProfile user) {
		
		UserProfile searchedUser = search(user.getUsername());
		
		if(compareScore(user, searchedUser) == user) {
			board.remove(searchedUser);
			searchedUser.transfereData(user);
			board.add(searchedUser);
			FileHandler.exportProfiles(this);
		}
	}
	
	/**
	 * 
	 * @param newUser
	 * @param user
	 * @return returns the user with the higher highscore
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
			if(name.equals(elem.getUsername())) {  //schema
				return elem;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public boolean hasProfile(String name) {
		
		if(search(name)!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean checkLoginData(String username, String password) {
		
		if(hasProfile(username)) {
			
			UserProfile user = search(username);
			if(user.checkPassword(password)) {
				return true;
			}else {
				displayIncorrectLoginDataPopup();
				return false;
			}
		}else {
			displayIncorrectLoginDataPopup();
			return false;
		}
	}
	
	/**
	 * Handles the incorrect data popup
	 */
	private void displayIncorrectLoginDataPopup() {
		
		Button close = new Button("Close");
		Label incorrectDataMessage = new Label("Incorrect username or password. "
				+ "Please try again.\n If you don't have an account, please register.");
		
		Stage popup = new Stage();
		close.setOnAction(e -> {
			popup.close();
		});
		
		VBox vbox = new VBox();
		vbox.getChildren().addAll(incorrectDataMessage, close);
		
		Scene scene = new Scene(vbox, 400, 200);
		
		popup.setTitle("Something's wrong...");
		popup.setScene(scene);
		popup.show();
	}
	
	public ArrayList<UserProfile> toArray(){
		
		ArrayList<UserProfile> users = new ArrayList<UserProfile>();
		PriorityQueue<UserProfile> aux = new PriorityQueue<>(board);
		
		while(!aux.isEmpty()) {
			users.add(aux.poll());
		}
		
		return users;
	}
	
	/**
	 * Displays the leaderboard as an array
	 * Here for testing purposes
	 */
	@Override
	public String toString() {
		
		PriorityQueue<UserProfile> aux = new PriorityQueue<>(board);
		String users = "";
		
		while(!aux.isEmpty()) {
			System.out.println(aux.remove());
			System.out.println();
		}
		return users;
	}
	
}