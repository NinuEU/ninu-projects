//    Team Kleenex
//    Gomoku-Server
//    
//    CSCE 320 / Spring 2011
//    Thursday, May 26, 2011
//    Sources: Java API w/ JDK

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * @author Nate Jackson
 *
 * This class interacts with the database(textfile) containing all the 
 * player information, and interacts with player objects.
 *
 */
public class PlayerData {

	private String buffer = ""; // A buffer to store strings.
	private ArrayList<Player> playerDatabase;
	private StringTokenizer st;
	private boolean databaseStarted = false;

	final private String filename = "playerDatabase.txt";

	/**
	 * Constructor that opens the database by calling openDatabase()
	 */
	public PlayerData() {
		openDatabase();
	}

	/**
	 * Takes in a name and a password that then is authenticated and
	 * put into the database. 
	 * 
	 * @param uname the players username
	 * @param pw the players password
	 * @return true if registration was successful. False if not, meaning
	 * the username is not availiable.
	 */
	public boolean registerPlayer(String uname, String pw) {

		if(databaseStarted) {
			if(!playerExist(uname)) {
				Player newPlayer = new Player(uname, pw, 0, 0, 0);
				playerDatabase.add(newPlayer);
				reWriteTF();
				return true;
			}else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * This method authenticates a player by checking the information of
	 * their username and their password against the database.
	 * 
	 * @param userName the username of the player
	 * @param password the password of the player
	 * @return true if that combination is successful, false if not.
	 */
	public boolean authenticatePlayer(String userName, String password){

		boolean auth = false;

		if(playerDatabase.size() > 0) {

			for(int i = 0;i < playerDatabase.size(); i++) {
				String uname = playerDatabase.get(i).getUsername();
				String pword = playerDatabase.get(i).getPassword();

				if(userName.equals(uname)) {
					if(password.equals(pword)) {
						auth = true;
						i = playerDatabase.size();
					}
				}
			}


		}

		return auth;
	}

	/**
	 * Checks a given username against the database to determine if it
	 * is already in use.
	 * 
	 * @param userName the username of the player 
	 * @return true if the username exists, false if not.
	 */
	public boolean playerExist(String userName){

		boolean exist = false;

		for(int i = 0;i < playerDatabase.size(); i++) {
			String uname = playerDatabase.get(i).getUsername();

			if(userName.equalsIgnoreCase(uname)) {

				exist = true;
			}
		}

		return exist;
	}
	
	/**
	 * 
	 * This is used to retrieve a certain player from the database via their
	 * username. If found, it returns a player object. If not, then returns null.
	 * 
	 * @param userName the username of a player
	 * @return a player object, or null if not found
	 */
	public Player getPlayer(String userName) {

		Player temp = null;

		if(playerDatabase.size() > 0) {

			for(int i = 0;i < playerDatabase.size(); i++) {
				String uname = playerDatabase.get(i).getUsername();

				if(userName.equals(uname)) {
					temp = playerDatabase.get(i);
					i = playerDatabase.size();
				}
			}

		}

		return temp;
	}

	/**
	 * Closes up the database by rewriting the text file, and, and setting
	 * the flag back to false.
	 */
	public void closeDatabase() {
		if(databaseStarted) {
			reWriteTF();
			databaseStarted = false;
		}
	}

	/**
	 * ReWrites the textfile holding the current state of the arraylist.
	 */
	public void backupDB() {
		if(databaseStarted) {
			reWriteTF();
			System.out.println("Database Backed Up");
		}
	}

	/**
	 * Opens up the database by loading  the list, and setting to true
	 * that the database is opened/loaded.
	 */
	public void openDatabase() {
		if(!databaseStarted) {
			loadupList();
			databaseStarted = true;
		}
	}

	/**
	 * This is a privat method used to reWrite the textfile from the arraylist
	 * with new player information.
	 */
	private void reWriteTF() {
		try {
			FileWriter writeStream = new FileWriter(filename);
			BufferedWriter out = new BufferedWriter(writeStream);

			for(int i = 0;i < playerDatabase.size();i++) {
				//System.out.println("poop");
				String outLine = playerDatabase.get(i).toString() + "\n";
				//System.out.println(outLine);
				out.write(outLine);
			}

			out.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * This private methods takes in the file of players, and loads it up
	 * into the array.
	 */
	private void loadupList() {

		playerDatabase = new ArrayList<Player>();

		File inputFile = new File(filename);

		try {
			Scanner freader = new Scanner(inputFile);

			while(freader.hasNext()) {
				buffer = freader.nextLine();
				st = new StringTokenizer(buffer);

				String un = st.nextToken();
				String pw = st.nextToken();
				int wins = Integer.parseInt(st.nextToken());
				int losses = Integer.parseInt(st.nextToken());
				int draws = Integer.parseInt(st.nextToken());

				Player inPlayer = new Player(un, pw, wins, losses, draws);

				playerDatabase.add(inPlayer);
				//System.out.println(inPlayer.toString() + " Has been loaded");
			}

			freader.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("playerDatabase.txt not found");
			System.exit(0);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("playerDatabase.txt player information format incorrect. \nPlease Correct and Restart Database/Server");
			System.exit(0);
		}

		//File Read in and what not stuff
	}
}




