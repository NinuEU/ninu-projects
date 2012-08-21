//    Team Kleenex
//    Gomoku-Server
//    
//    CSCE 320 / Spring 2011
//    Thursday, May 26, 2011
//    Sources: Java API w/ JDK

import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


/**
 * This class is the main external portion of the server
 * that handles incoming connections. It constantly reads
 * for connections, and registers them with the model.
 * 
 * @author Nate Jackson
 */
public class ServerController extends Thread{

	//A Thread
	private Thread w;
	//A ServerModel 
	private ServerModel sModel;
	//A ServerGUI
	private ServerGUI sView;

	/**
	 * The constructor takes in the model, and creates the thread
	 * to be run in this class.
	 * 
	 * @param m the Model class being used
	 */
	ServerController(ServerModel m, ServerGUI theView) {
		//Assigning Model
		sModel = m;

		sView = theView;

		//Creating Thread
		w = new Thread(this);

		theView.startButtonListener(new startListener());
		theView.stopButtonListener(new stopListener());
		theView.backupButtonListener(new backupListener());
	}

	/**
	 * This method starts the server by running the Thread
	 * to accept connections.
	 */
	private void startServer() {
		//Calling start() executes the run method
		if(!w.isAlive()) {
			sModel.runServerConnection();
			w.start();
		}
	}

	/**
	 * This is an inherited method from the Thread class that is run
	 * outside of the class. With this implementation, the run method 
	 * constantly listens for incoming connections to the server, and
	 * registers them with the Model.
	 */
	public void run() {

		System.out.println("-------Server Started-------");
		incomingServerInfo("-------Server Started-------");

		boolean runFlag = true;
		while(runFlag == true){
			try {
				//Incoming Connection Assignment
				Socket connection = sModel.getMainSocket().accept();
				
				//Creating a New Connection Object
				SerCon connect = new SerCon(connection, sModel);
				//Registering New Connection
				sModel.addConnection(connect);

				//Commandline Log: Server Connecting
				String outInfo = "";
				outInfo += sModel.currentTime() + " | " + connection.getLocalAddress() + ":" + connection.getPort() + " Has Connected.";
				System.out.println(outInfo);
				incomingServerInfo(outInfo);
				sView.updateUserList(sModel.connectionList());
			} catch (IOException e) {
				System.out.println("Problem With The Server Accepting Incoming Connections");
				incomingServerInfo("Problem With The Server Accepting Incoming Connections");
				runFlag = false;
			} catch (NullPointerException e) {
				System.out.println("Server Port Does Not Exist");
				incomingServerInfo("Server Port Does Not Exist");
				runFlag = false;
			}
		}
	}

	/**
	 * This method shuts down the server by stopping the Thread
	 * accepting connections.
	 */
	public void shutdownServer() {
		//Calling start() executes the run method
		if(w.isAlive()){
			w.stop();
			w = new Thread(this);
			sModel.closeServerConnection();
		}

	}
	
	/**
	 * This method sends information in the form of a 
	 * string to the designated ServerGUI
	 * @param input the string to send
	 */
	public void incomingServerInfo(String input) {
		sView.incomingInfo(input);
	}

	/**
	 * This method calls the ServerGUI to update it's list
	 * of connected and online players.
	 */
	public void serverListUpdate() {
		sView.updateUserList(sModel.connectionList());
	}

	//Buttons
	/**
	 * The action listener for the Start Server button.
	 */
	class startListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			startServer();
		}
	}	

	/**
	 * The action listener for the Shutdown Server button.
	 */
	class stopListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			shutdownServer();
		}
	}

	/**
	 * The action listener for the BackupDatabase Button. 
	 */
	class backupListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if(w.isAlive()) {
				sModel.backupDatabase();
			}
		}
	}
}
