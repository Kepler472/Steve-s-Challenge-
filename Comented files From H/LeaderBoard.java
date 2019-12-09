import java.util.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * This class is the LeaderBoard where the top scores are scored.
 * @author Horatiu Filip
 */
public class LeaderBoard {

	private PriorityQueue<UserProfile> board;

	/**
	 * Initialize the LeaderBoard.
	 */
	public LeaderBoard() {

		board = new PriorityQueue<UserProfile>();
		FileHandler.importProfiles(this);
	}

	/**
	 * Get the leaderboard.
	 * @return the leaderboard.
	 */
	public PriorityQueue<UserProfile> getBoard(){
		return this.board;
	}

	/**
	 * Add user to the leaderboard.
	 * @param user The user to be added.
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

	/**
	 * Get the top scores for a given level.
	 * @param level The level checked.
	 * @return The top scores.
	 */
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
	 * Update existing profile.
	 * @param user The profile to be updated.
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
	 * Compare the scores between 2 profiles.
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
	 * Search the board for the given user.
	 * @param name The name of the user.
	 * @return The profile of the user if he exists, null otherwise.
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
	 * Checks if the name of a user exists.
	 * @param name The name searched for.
	 * @return Returns true if he exists, false otherwise.
	 */
	public boolean hasProfile(String name) {

		if(search(name)!=null) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Check if the credentials entered are correct.
	 * @param username
	 * @param password
	 * @return True if the credentials match, false otherwise.
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
	 * Execute the incorrect credentials message.
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

	/**
	 * Get the user profiles in an ArrayList.
	 * @return The arraylist of players.
	 */
	public ArrayList<UserProfile> toArray(){

		ArrayList<UserProfile> users = new ArrayList<UserProfile>();
		PriorityQueue<UserProfile> aux = new PriorityQueue<>(board);

		while(!aux.isEmpty()) {
			users.add(aux.poll());
		}

		return users;
	}

	/**
	 * Print out the board on the screen.
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
