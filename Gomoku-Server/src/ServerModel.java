//    Team Kleenex
//    Gomoku-Server
//    
//    CSCE 320 / Spring 2011
//    Thursday, May 26, 2011
//    Sources: Java API w/ JDK

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 * This class is the Model for the server. This takes care of all
 * of the information needed to be tracked. It also opens up the Server
 * port to be used. 
 * 
 * @author Nate Jackson
 *
 */
public class ServerModel {

	//ServerPort
	private int port = 54321;
	//ServerSocket
	private ServerSocket mainSocket;

	//ArrayList of Connections To The Server
	private ArrayList<SerCon> conList;

	//Logged In Connections
	private ArrayList<SerCon> loggedConnections;

	//Logged In Connections in the lobby
	private ArrayList<SerCon> lobbyConnections;
	
	//The server's controller class
	private ServerController sCon;

	//the server's player database
	private PlayerData pDatabase;

	/**
	 *  Constructor for the ServerModel
	 */
	public ServerModel() {

	}

	/**
	 * Prepares the server to accept incoming connections for the game.
	 */
	public void runServerConnection() {
		try {
			//Setup The Server Socket
			mainSocket = new ServerSocket(port);
			conList = new ArrayList<SerCon>();
			loggedConnections = new ArrayList<SerCon>();
			lobbyConnections = new ArrayList<SerCon>();
			pDatabase = new PlayerData();
			
		} catch (IOException e) {
			System.out.println("ServerSocket Port Unable To Be Opened. Possibly Already In Use");
		}
	}

	public void closeServerConnection() {
		if(!mainSocket.isClosed()) {
			try {
				for(int i = 0;i < conList.size();i++) {
					conList.get(i).closeConnection();
				}

				mainSocket.close();
				pDatabase.closeDatabase();
				conList = new ArrayList<SerCon>();
				loggedConnections = new ArrayList<SerCon>();
				lobbyConnections = new ArrayList<SerCon>();
				sCon.serverListUpdate();
				sCon.incomingServerInfo("-------Server Shutdown-------");
				System.out.println("-------Server Shutdown-------");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method takes a connection, and add it to the list of
	 * connections tracked by the ServerModel class. Once added, a
	 * message is then sent out to the connection notifying them
	 * that they are connected. Also, once added, the connection
	 * is then started. 
	 * 
	 * @param x a brand new connection to the server
	 */
	public void addConnection(SerCon x) {
		//Add to ArrayList

		conList.add(x);
		//Displays a Message To The New Connection
		x.outMessage("YOU ARE CONNECTED TO THE MEGA SERVER! ALMIGHTY SERVER!");
		//Runs The Connection
		x.runConnection();
	}

	/**
	 * This takes in a Server Connection, and adds it to the Logged In List
	 * 
	 * @param x a SerCon Server Connection
	 */
	public void addLoggedConnection(SerCon x) {
		//Add to ArrayList
		loggedConnections.add(x);
		//Displays a Message To The New Connection
		//x.outMessage("YOU ARE CONNECTED TO THE MEGA SERVER! ALMIGHTY SERVER!");
		//Runs The Connection
		//x.runConnection();
	}

	/**
	 * This takes in a Server Connection, and adds it to the Lobby In List
	 * 
	 * @param x a SerCon Server Connection
	 */
	public void addLobbyConnection(SerCon x) {
		//Add to ArrayList
		if(!(isLobby(x.getPlayer().getUsername()))) {
			lobbyConnections.add(x);
		}
		//Displays a Message To The New Connection
		//x.outMessage("YOU ARE CONNECTED TO THE MEGA SERVER! ALMIGHTY SERVER!");
		//Runs The Connection
		//x.runConnection();
		//this.updateLobbyConnections();
	}

	/**
	 * This methods removes a connection from the System. It takes in 
	 * a socket, and looks for it in the ArrayList containing all of the
	 * registered connections. When it is found, it removes it, and closes 
	 * up the connection as well. It also displays a message in the Console
	 * saying that the connection was removed.
	 * 
	 * @param soc the socket wanting to be removed.
	 */
	public void removeConnection(Socket soc) {

		//Temporary ServerConnection Holder
		SerCon tempSoc = null;

		//Remove From Actual Server Connections
		for(int i = 0;i < conList.size();i++) {
			//Checks to See If The Sockets Match
			if(conList.get(i).getSocket().equals(soc)) {
				//Temporarily Assigns the Socket
				tempSoc = conList.get(i);
				sCon.incomingServerInfo(this.currentTime() + " | " + tempSoc.getIPP() + " Has Disconnected");
				//Removes The Server Connection From The ArrayList
				conList.remove(i);
				sCon.serverListUpdate();
				//Closes The Connection
				tempSoc.closeConnection();
				//Displays a Message
				//sCon.serverListUpdate();

				System.out.println("Connection Has Been Dropped");
				sCon.incomingServerInfo("Connection Has Been Dropped");
			}
		}

		removeLoggedConnection(soc);

	}

	/**
	 * This methods removes a connection from the Logged Connections List. It takes in 
	 * a socket, and looks for it in the ArrayList containing all of the
	 * registered connections. When it is found, it removes it, and closes 
	 * up the connection as well. It also displays a message in the Console
	 * saying that the connection was removed.
	 * 
	 * @param soc the socket wanting to be removed.
	 */
	public void removeLoggedConnection(Socket soc) {

		//Temporary ServerConnection Holder
		SerCon tempSoc = null;

		//Remove From Actual Server Connections
		for(int i = 0;i < loggedConnections.size();i++) {
			//Checks to See If The Sockets Match
			if(loggedConnections.get(i).getSocket().equals(soc)) {
				//Temporarily Assigns the Socket
				tempSoc = loggedConnections.get(i);
				//sCon.incomingServerInfo(tempSoc.getIPP() + "has Disconnected");
				//Removes The Server Connection From The ArrayList
				loggedConnections.remove(i);

				System.out.println("Logged Connection Has Been Dropped");
				sCon.incomingServerInfo("Logged Connection Has Been Dropped");
			}
		}

	}

	/**
	 * This methods removes a connection from the Lobby Connections List. It takes in 
	 * a socket, and looks for it in the ArrayList containing all of the
	 * registered connections. When it is found, it removes it, and closes 
	 * up the connection as well. It also displays a message in the Console
	 * saying that the connection was removed.
	 * 
	 * @param soc the socket wanting to be removed.
	 */
	public void removeLobbyConnection(Socket soc) {
		//Temporary ServerConnection Holder
		SerCon tempSoc = null;

		//Remove From Actual Server Connections
		for(int i = 0;i < lobbyConnections.size();i++) {
			//Checks to See If The Sockets Match
			if(lobbyConnections.get(i).getSocket().equals(soc)) {
				//Temporarily Assigns the Socket
				tempSoc = lobbyConnections.get(i);
				//sCon.incomingServerInfo(tempSoc.getIPP() + "has Disconnected");
				//Removes The Server Connection From The ArrayList
				lobbyConnections.remove(i);

				//				this.updateLobbyConnections();

				System.out.println("LobbyConnection Connection Has Been Dropped");
				sCon.incomingServerInfo("LobbyConnection Connection Has Been Dropped");
			}
		}
	}

	/**
	 * This is a get method returning the ServerSocket for
	 * the server. 
	 * 
	 * @return the mainSocket to the server
	 */
	public ServerSocket getMainSocket() {
		return mainSocket;
	}

	/**
	 * This is a convenience method used to return the
	 * current time of the system with hours, minutes,
	 * and seconds. 
	 * 
	 * @return the current system time.
	 */
	public String currentTime() {
		//Creates Calendar Object
		Calendar cal = Calendar.getInstance();
		//Creats a Time String
		String theTime = String.format("%02d", cal.get(Calendar.HOUR_OF_DAY)) + ":" + String.format("%02d", cal.get(Calendar.MINUTE)) 
		+ ":" + String.format("%02d", cal.get(Calendar.SECOND));;
		//Returns Time String
		return theTime;
	}

	/**
	 * Returns a list of connections to the server in an ArrayList of SerCon objects.
	 * 
	 * @return ArrayList of SerCon objects of the list of connections.
	 */
	public ArrayList<SerCon> connectionList() {

		/*
		ArrayList<String> a = new ArrayList<String>();

		for(int i = 0;i < conList.size();i++) {
			a.add(conList.get(i).getIPP());
		}

		return a;
		 */

		return conList;
	}

	/**
	 * Returns a list of connections in the lobby list in an ArrayList of SerCon 
	 * objects.
	 * 
	 * @return ArrayList of SerCon objects of the lobby liist
	 */
	public ArrayList<String> lobbyList() {

		ArrayList<String> a = new ArrayList<String>();

		for(int i = 0;i < lobbyConnections.size();i++) {
			a.add(lobbyConnections.get(i).getPlayer().getUsername());
		}

		return a;
	}

	/**
	 * Sets the server Controller that listens for connections
	 * 
	 * @param serverController the controller for incoming connections
	 */
	public void connectController(ServerController serverController) {
		sCon = serverController;
	}

	/**
	 * calls backupDB() method for the player database to store the current
	 * list of players in the database.
	 */
	public void backupDatabase() {

		pDatabase.backupDB();
	}


	//Major Client Interactions=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

	/**
	 * Registers a player in the database, and returns weather they registered 
	 * successfully or not.
	 * 
	 * @return 1 if register was sucessful, 0 if unsucessful.
	 */
	public int playerRegister(String uname, String pword) {

		if(pDatabase.registerPlayer(uname, pword)) {
			pDatabase.backupDB();
			return 1;
		}
		else {
			return 0;
		}
	}

	/**
	 * This logs a player into the server System.
	 * 
	 * @param uname the players username
	 * @param pword the players password
	 * @return 1 of sucessful, 0 if unsucessful
	 */
	public int playerLogin(String uname, String pword) {

		if(pDatabase.authenticatePlayer(uname, pword)) {
			return 1;
		}
		else {
			return 0;
		}

	}

	/**
	 * returns a string of lobby players via their toStrings() methods calls.
	 * 
	 * @return 
	 */
	public String lobbyPlayers() {
		String bigString = "";

		for(int i = 0;i < lobbyConnections.size();i++) {
			//Player 
			bigString += lobbyConnections.get(i).getPlayer().pInfoStuff() + " ";
		}

		return bigString;

	}

	/**
	 * This method passes a match request on from the requesting client
	 * to the requested client. 
	 * 
	 * @param usernameFrom username of the requesting client
	 * @param usernameTo username of the requested client
	 * @param serverSocketIP socket IP address of the requesting client
	 * @param serverSocketPort socket Port of the requesting client
	 */
	public void requestMatch(String usernameFrom, String usernameTo, 
			String serverSocketIP, String serverSocketPort, String gameSize) {
		//TODO start the request timer
		Iterator<SerCon> it = lobbyConnections.iterator();
		while(it.hasNext()){
			SerCon con = it.next();
			if(con.getPlayer().getUsername().equals(usernameTo)){
				String out = "/requestMatch "+usernameFrom+" "+serverSocketIP+" "+serverSocketPort+" "+gameSize;
				sCon.incomingServerInfo(currentTime()+" | "+con.getIPP()+" - "+out);
				con.outMessage(out);
				break;
			}
		}
	}


	/**
	 * This method removes two clients from the lobbyConnection 
	 * list, indicating that the two players are now in a game.
	 * 
	 * @param usernameFrom the client "accepting the request"
	 * @param usernameTo the client being accepted
	 */
	public void requestAccept(String usernameFrom, String usernameTo) {
		// TODO cancel the request timer

		//digs through lobbyConnections for the username, then calls
		//removeLobbyConnection with the socket associated with that username.
		for(int i=0; i<lobbyConnections.size(); i++){
			if(lobbyConnections.get(i).getPlayer().getUsername().equals(usernameFrom)){
				removeLobbyConnection(lobbyConnections.get(i).getSocket());
				break;
			}
		}
		for(int i=0; i<lobbyConnections.size(); i++){
			if(lobbyConnections.get(i).getPlayer().getUsername().equals(usernameTo)){
				lobbyConnections.get(i).outMessage("/requestAccept");
				removeLobbyConnection(lobbyConnections.get(i).getSocket());
				break;
			}	
		}
		//this.updateLobbyConnections();
	}

	/**
	 * Sends a /requestRefuse to usernameTo from usernameFrom
	 * 
	 * @param usernameFrom the client "refusing the request"
	 * @param usernameTo the client receiving the message.
	 */
	public void requestRefuse(String usernameFrom, String usernameTo) {
		//TODO cancel the countdown

		//looks through the lobbyConnections instead of loggedConnections since nothing 
		//should happen anyway if the player is logged in but not in the lobby. (all requests
		//would be canceled when a user starts a game and is no longer in lobbyConnections.
		for(int i=0; i<lobbyConnections.size(); i++){
			if(lobbyConnections.get(i).getPlayer().getUsername().equals(usernameTo)){
				lobbyConnections.get(i).outMessage("/requestRefuse "+usernameFrom);
				break;
			}
		}		
	}

	/**
	 * this sets out an updates playerlist command to all the connections
	 * in the lobby list.
	 */
	public void updateLobbyConnections() {

		for(int i = 0;i < lobbyConnections.size();i++) {

			String stringy = "/playerList ";
			stringy += this.lobbyPlayers();
			lobbyConnections.get(i).outMessage(stringy);
		}
		sCon.serverListUpdate();
	}

	/**
	 * Gets a player frmo teh PlayerDatabase VIA their username
	 * 
	 * @param pname the username of the player
	 * @return the player if found, or a null if not
	 */
	public Player getPlayer(String pname) {
		return pDatabase.getPlayer(pname);
	}

	/**
	 * Checks to see if a player is in logged in, via if there are in
	 * the logged in list of connections.
	 * 
	 * @param name the username of the player
	 * @return true if the player is logged in, false if they are not
	 */
	public boolean isLoggedIn(String name) {

		boolean isLogged = false;

		for(int i = 0;i < loggedConnections.size();i++) {
			//Checks to See If The Sockets Match
			if(loggedConnections.get(i).getPlayer().getUsername().equalsIgnoreCase(name)) {
				isLogged = true;
			}
		}

		return isLogged;

	}

	/**
	 * Checks to see if somone is in the lobby via the lobby list.
	 * 
	 * @param name the username of the player
	 * @return true if the connection is in the lobby, false if not.
	 */
	public boolean isLobby(String name) {

		boolean isLobbied = false;

		for(int i = 0;i < lobbyConnections.size();i++) {
			//Checks to See If The Sockets Match
			if(lobbyConnections.get(i).getPlayer().getUsername().equalsIgnoreCase(name)) {
				isLobbied = true;
			}
		}

		return isLobbied;

	}

	/**
	 * Updates the lists in the server GUI
	 */
	public void updateConnectionListGUI() {
		sCon.serverListUpdate();
	}

}
