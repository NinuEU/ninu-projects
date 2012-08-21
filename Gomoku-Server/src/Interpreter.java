//    Team Kleenex
//    Gomoku-Server
//    
//    CSCE 320 / Spring 2011
//    Thursday, May 26, 2011
//    Sources: Java API w/ JDK

import java.util.StringTokenizer;

/**
 * Class Interpreter acts as the bridge between the SerCon and
 * the client. It's main act is to decode the messages from the
 * client and dictate their functionality to the the server model.
 * 
 * @author Karl Bayer
 */
public class Interpreter{

	private ServerModel model; 
	private SerCon connection;

	/**
	 * Sets the server's ServerModel object.
	 * @param model the model
	 */
	protected void setModel(ServerModel model) {
		this.model = model;
	}

	/**
	 * Sets the reference to the SerCon object 
	 * for this interpreter.
	 * @param connnection the SerCon object
	 */
	protected void setSerCon(SerCon connection){
		this.connection = connection;
	}

	/**
	 * Constructor including references to the model, 
	 * and the server connection.
	 * 
	 * @param model the model
	 * @param connection the SerCon object
	 */
	public Interpreter(ServerModel model, SerCon connection){
		this.model = model; 
		this.connection = connection;
	}


	/**
	 * The interpret method is the main interpreter method. It takes
	 * as an input an individual string, determines the instruction, 
	 * if any, and calls the appropriate method in the model.
	 * 
	 * @param str the instruction from the client
	 */
	public void interpret(String str){
		//reads the string into an array
		String[] tokens = read(str);

		//read the instruction and execute
		String instruction = "";
		if(tokens.length > 0) {
			instruction = tokens[0];
		}

		// /register [username] [password]
		if (instruction.equals("/register") && tokens.length == 3){
			System.out.println(model.currentTime()+" | "+connection.getIPP()+str);
			String outcome = "/registerSuccess "; //the success message
			//execute register and send result
			outcome += model.playerRegister(tokens[1],tokens[2]);
			connection.outMessage(outcome);
		}

		// 	/login [username] [password]
		else if (instruction.equals("/login") && tokens.length == 3){
			System.out.println(model.currentTime()+" | "+connection.getIPP()+str);
			String outcome = "/loginSuccess "; //the success message
			int logSuc = -1;

			//if the player is already logged in...
			if(model.isLoggedIn(tokens[1])) {
				outcome += "0";
				connection.outMessage(outcome);
			}
			//if the player is not already logged in...
			else {
				//execute login
				logSuc = model.playerLogin(tokens[1],tokens[2]);
				if(logSuc == 1) {
					//add Player's reference to SerCon
					Player foundPlayer = null;
					foundPlayer = model.getPlayer(tokens[1]);
					connection.setPlayer(foundPlayer);
					//add the player to the Logged-In list
					model.addLoggedConnection(connection);
					//add the player to the Lobby list
					model.addLobbyConnection(connection);

					outcome += model.playerLogin(tokens[1],tokens[2]);
					//send the login result and a new player list
					connection.outMessage(outcome);
					model.updateLobbyConnections();
				} else {
					//send the login result
					outcome += model.playerLogin(tokens[1],tokens[2]);
					connection.outMessage(outcome);
				}
			}
		}

		// 	/updateWin
		else if (instruction.equals("/updateWin") && tokens.length == 1){
			System.out.println(model.currentTime()+" | "+connection.getIPP()+str);
			//increment wins
			Player x = connection.getPlayer();
			if(x != null) {
				x.incWin();
				model.backupDatabase();
				//add to lobbylist and update the playerlist
				model.addLobbyConnection(connection);
				model.updateLobbyConnections();
			}
			else {
				System.out.println("NO UPDATE FOR YOU!");
				connection.outMessage("YOU CAN'T UPDATE! CHEATER!");
			}
		}

		// 	/updateLoss
		else if (instruction.equals("/updateLoss") && tokens.length == 1){
			System.out.println(model.currentTime()+" | "+connection.getIPP()+str);
			//increment loss
			Player x = connection.getPlayer();
			if(x != null ) {
				x.incLoss();
				model.backupDatabase();
				//add to lobbylist and update the playerlist
				model.addLobbyConnection(connection);
				model.updateLobbyConnections();
			}
			else {
				System.out.println("NO UPDATE FOR YOU!");
				connection.outMessage("YOU CAN'T UPDATE! CHEATER!");
			}
		}

		// 	/updateDraw
		else if (instruction.equals("/updateDraw") && tokens.length == 1){
			System.out.println(model.currentTime()+" | "+connection.getIPP()+str);
			//increment withdraw
			Player x = connection.getPlayer();
			//System.out.println(x.toString());
			if(x != null) {
				x.incDraw();
				model.backupDatabase();
				//add to lobbylist and update the playerlist
				System.out.println(x.toString());
				model.addLobbyConnection(connection);
				model.updateLobbyConnections();
			}
			else {
				System.out.println("NO UPDATE FOR YOU!");
				connection.outMessage("YOU CAN'T UPDATE! CHEATER!");
			}
		}

		// 	/getPlayerList
		else if (instruction.equals("/getPlayerList") && tokens.length == 1){
			System.out.println(model.currentTime()+" | "+connection.getIPP()+str);
			if(connection.getPlayer() != null) {
				//compile the current player list
				String stringy = "/playerList ";
				stringy += model.lobbyPlayers();
				//send out the player list
				connection.outMessage(stringy);
			}
		}

		//  /logout
		else if (instruction.equals("/logout") && tokens.length == 1){
			System.out.println(model.currentTime()+" | "+connection.getIPP()+str);
			//remove the lobby and logged connections
			model.removeLobbyConnection(connection.getSocket());
			model.removeLoggedConnection(connection.getSocket());
			//remove SerCon's association to the player
			connection.setPlayer(null);
			model.updateConnectionListGUI();
			//send out the new player list
			model.updateLobbyConnections();
		}

		// 	/requestMatch [username] [ServerSocketIP] [ServerSocketPORT] [boardSize]
		else if (instruction.equals("/requestMatch") && tokens.length == 5){
			System.out.println(model.currentTime()+" | "+connection.getIPP()+str);
			if(connection.getPlayer() != null) {
				//check that the both players are in the lobby
				if(model.isLobby(tokens[1]) && model.isLobby(connection.getPlayer().getUsername())) {
					//send the request
					model.requestMatch(connection.getPlayer().getUsername(),tokens[1],tokens[2],tokens[3],tokens[4]);
				}
				else {
					connection.outMessage("Either You Or Your Opponent Are In The Lobby");
				}
			}
			else {
				connection.outMessage("Hahaha Nice Try Pal! Try logging in! *POW!*");
			}
		}


		// /requestAccept [username]
		else if (instruction.equals("/requestAccept") && tokens.length == 2){
			System.out.println(model.currentTime()+" | "+connection.getIPP()+str);
			if(connection.getPlayer() != null) {

				if(model.isLobby(tokens[1]) && model.isLobby(connection.getPlayer().getUsername())) {
					model.requestAccept(connection.getPlayer().getUsername(),tokens[1]);
					model.updateConnectionListGUI();
				}
				else {
					connection.outMessage("Either You Or Your Opponent Are In The Lobby");
				}
			}
			else {
				connection.outMessage("Hahaha Nice Try Pal! Try logging in! *POW!*");
			}
		}

		// /requestRefuse [username]
		else if (instruction.equals("/requestRefuse") && tokens.length == 2){
			System.out.println(model.currentTime()+" | "+connection.getIPP()+str);
			if(connection.getPlayer() != null) {
				if(model.isLobby(tokens[1]) && model.isLobby(connection.getPlayer().getUsername())) {
					model.requestRefuse(connection.getPlayer().getUsername(),tokens[1]);
				}
				else {
					connection.outMessage("Either You Or Your Opponent Are In The Lobby");
				}
			}
			else {
				connection.outMessage("Hahaha Nice Try Pal! Try logging in! *POW!*");
			}
		}

		// 
		else {
			//should never be here
			System.out.println(model.currentTime()+" | "+connection.getIPP()+
					" sent ["+str+"] Error: no matched instruction");
			connection.outMessage("huh? that command ain't gonna work!");
		}
	}

	/**
	 * The read method's only function is to split the input string
	 * into its appropriate tokens by a white space delimiter.
	 * 
	 * @param str the input string to be split
	 * @return an array of strings
	 */
	private String[] read(String str){
		//tokenizer for the string
		StringTokenizer tokenizer = new StringTokenizer(str);
		//total number of tokens
		int length = tokenizer.countTokens();
		//array of length 'length' to hold the tokens
		String[] tokens = new String[tokenizer.countTokens()];

		//read each token into the array
		for(int i=0; i<length; i++){
			tokens[i]=tokenizer.nextToken();
		}
		return tokens;
	}
}