//    Team Kleenex
//    Gomoku-Server
//    
//    CSCE 320 / Spring 2011
//    Thursday, May 26, 2011
//    Sources: Java API w/ JDK

/**
 * This class represents a registered player of Gomoku. It contains
 * that players username, password, and game statistics of win, loss, 
 * and draw, 
 * and methods to interact with the data. 
 * 
 * @author Team Kleenex
 *
 */
public class Player {

	private String username;
	private String password;
	private int win;
	private int loss;
	private int draw;

	/**
	 * The constructor for this Player object. It sets the username,
	 * password, win, loss, and draw parameters.
	 * @param uname the username
	 * @param pw the password
	 * @param w	the wins
	 * @param l the losses
	 * @param d the draws
	 */
	public Player(String uname, String pw, int w, int l, int d) {
		
		setUsername(uname);
		setPassword(pw);
		setWins(w);
		setLosses(l);
		setDraws(d);
		
	}

	/**
	 * Sets the username for player.
	 * 
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Returns the username for the player
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the password for the player
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns the password for the player
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the number of wins for the player
	 * 
	 * @param win the win to set
	 */
	public void setWins(int win) {
		this.win = win;
	}

	/**
	 * Returns the number of wins for the player
	 * 
	 * @return the win
	 */
	public int getWins() {
		return win;
	}

	/**
	 * Sets the number of losses for the player
	 * 
	 * @param loss the loss to set
	 */
	public void setLosses(int loss) {
		this.loss = loss;
	}

	/**
	 * Returns the number of losses for the player
	 * 
	 * @return the loss
	 */
	public int getLosses() {
		return loss;
	}

	/**
	 * Sets the number of draws for the player
	 * 
	 * @param draw the draw to set
	 */
	public void setDraws(int draw) {
		this.draw = draw;
	}

	/**
	 * Returns the number of wins for the player
	 * 
	 * @return the draw
	 */
	public int getDraws() {
		return draw;
	}

	/**
	 * Increments the win parameter of this Player
	 */
	public void incWin() {
		win++;
	}
	
	/**
	 * Increments the loss parameter of this Player
	 */
	public void incLoss() {
		loss++;
	}
	
	/**
	 * Increments the draw parameter of this Player
	 */
	public void incDraw() {
		draw++;
	}
	
	/**
	 * This method compiles a string representation of this
	 * player object.
	 * @return the string representation of this.
	 */
	public String toString() {
		String retString = "";
		
		retString += username + " ";
		retString += password + " ";
		retString += win + " ";
		retString += loss + " ";
		retString += draw;
		//retString += "\n";
		return retString;
		
	}
	
	/**
	 * This method compiles a string representation of this
	 * player object without the password.
	 * @return the string representation of this.
	 */
	public String pInfoStuff() {
		String retString = "";
		
		retString += username + " ";
		retString += win + " ";
		retString += loss + " ";
		retString += draw;
		return retString;
	}
}
