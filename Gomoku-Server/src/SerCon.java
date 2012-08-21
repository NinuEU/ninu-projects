//    Team Kleenex
//    Gomoku-Server
//    
//    CSCE 320 / Spring 2011
//    Thursday, May 26, 2011
//    Sources: Java API w/ JDK

import java.net.*;
import java.util.Calendar;
import java.io.*;

//Class To Manage Server connections
/**
 * SerCon is a class that represents an incoming connection to the
 * server. This is created to house the information about the connection,
 * as well as takes care of the reading and writing to that connection
 * to and from the server.
 * 
 */
public class SerCon extends Thread {

	private Thread q; //Creating a Thread
	public Socket socketToMe; //Creating a Client Socket
	private ServerModel servMod; // ServerModel
	private OutputStream outStream; //PrintWriter Stream
	private InputStream inStream; //Buffer Stream
	private Player pInfo;
	
	private Interpreter interp;// Interpreter
	

	/**
	 * This is the constructor for the class that sets up the the
	 * socket, as well as declares the IO streams. 
	 * 
	 * @param soc the Socket for this connection
	 * @param servM a reference to the ServerModel being used
	 */
	public SerCon(Socket soc,ServerModel servM) {
		try {
			//Sets Up The Socket
			socketToMe = soc;
						
			//Input IO Stream			
			inStream = socketToMe.getInputStream();
			//Output IO Stream
			outStream = socketToMe.getOutputStream();
			//The associated player on login
			pInfo = null;

		} catch (IOException e) {
			e.printStackTrace();
		}
		//Sets the ServerModel
		servMod = servM;
		//Creates The Thread
		
		//Interpreter for the input
		interp = new Interpreter(servM,this);
		
		q = new Thread(this);
	}

	/**
	 * This is the run method overridden in this class from the Thread
	 * class. This run method is a look that reads from the connected client,
	 * and whenever they send input to the server, it is sent to the interpreter.
	 */
	public void run() {
		//Incoming Message 
		String msg;
		boolean loopFlag = true;

		//Loop Always Listening for Client Input
		while (loopFlag == true) {
			try {
				//Checks to see if the input is legit				
				
				byte[] bufin = new byte[1000];
				//if(inStream.ready()) {
				int len = inStream.read(bufin);
				if(len > 0)  {
					//the incomming message	
					msg = new String(bufin, 0, len);
					
					//interpret the message
					interp.interpret(msg);
					
					//System.out.println(msg);
				}
				else {
					
					//Disconnected Server Information
					System.out.println("Steven is looking...");
					System.out.println(servMod.currentTime() + " | " + getIPP() + " has Disconnected");
					//Sets Flag To End Loop
					//Removes and Closes Connection
					servMod.removeLobbyConnection(socketToMe);
					servMod.removeLoggedConnection(socketToMe);
					servMod.removeConnection(socketToMe);
					servMod.updateLobbyConnections();
					loopFlag = false;
					System.out.println("reached the end");
					
					//System.out.println("POOPY CAKES!");
				}
				


			} catch (IOException e) {
				System.out.println("Disconnecting Connection");
				System.out.println(servMod.currentTime() + " | " + getIPP() + " has Disconnected");
				servMod.removeLobbyConnection(socketToMe);
				servMod.removeLoggedConnection(socketToMe);
				servMod.removeConnection(socketToMe);
				servMod.updateLobbyConnections();
				loopFlag = false;			
			}
		}
	}

	/**
	 * This method sends a message to the client connection
	 * from the server.
	 * 
	 * @param msg the message to be sent
	 */
	public void outMessage(String msg) {
		byte[] bufout = new byte[1000];;
		bufout = msg.getBytes();
		
		try {
			outStream.write(bufout);
			System.out.println("Sending To " + this.getIPP() + ": " + msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * This method is used to execute the run method inherited
	 * from Thread, which starts up the connection reading input 
	 * from the client being sent to the Server.
	 */
	public void runConnection() {
		q.start();
	}

	/**
	 * This method is used to close up this connection completely.
	 */
	public void closeConnection() {
		try {
			q.stop();
			socketToMe.close();
			outStream.close();
			inStream.close();
		} catch (IOException e) {
			System.out.println("Problem Closing Connection");
		}
	}

	/**
	 * This method returns the socket information for this connection
	 * 
	 * @return the socket information for this connection
	 */
	public Socket getSocket() {
		return socketToMe;
	}
	
	/**
	 * This method gets the IP address and Port for this connection
	 * in a readable form.
	 * @return a string representing the IP and Port
	 */
	public String getIPP() {
		
		String iAdd = "";
		
		iAdd += socketToMe.getLocalAddress() + ":" + socketToMe.getPort();
		return iAdd;
	}
	
	/**
	 * Sets the pInfo reference to a player.
	 * @param p the player to be referenced
	 */
	public void setPlayer(Player p) {
		pInfo = p;
	}
	
	/**
	 * Gets the pInfo reference to the player.
	 * @return the referenced player
	 */
	public Player getPlayer(){
		return pInfo;
	}
	
}
