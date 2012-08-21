//    Team Kleenex
//    Gomoku-Server
//    
//    CSCE 320 / Spring 2011
//    Thursday, May 26, 2011
//    Sources: Java API w/ JDK

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * This class is the Server's main GUI class. It contains 
 * the GUI components and methods to interact with them. 
 * 
 * @author Team Kleenex
 *
 */
public class ServerGUI extends JFrame {

	//Panels
	private JPanel mainPanel;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	
	//Buttons
	private JButton startButton;
	private JButton stopButton;
	private JButton backupButton;
	
	//JScrollPane
	private JScrollPane scrollPane;
	private JScrollPane userScrollPane;

	//TextArea
	private JTextArea bigField;
	private JTextArea userList;
	private JTextArea lobbyList;
	//Window Size
	private final int W_WIDTH = 800;
	private final int W_HEIGHT = 620;

	/**
	 * Constructor for the Client View Class.
	 */
	public ServerGUI(){
		//Setting Initial Information
		setTitle("Server Console");
		setSize(W_WIDTH,W_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Builds The Panel
		buildPanel();
		//Adds the Main Panel
		add(mainPanel);
		//Sets Makes it Visible
		setVisible(true);
	}

	/**
	 * Builds the Client GUI
	 */
	private void buildPanel() {

		//Initializes Panels
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();

		//Sets Up a Scroll Bar
		scrollPane = new JScrollPane(panel1);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		userScrollPane = new JScrollPane(panel3);
		userScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		//Buttons
		startButton = new JButton("Start Server");
		stopButton = new JButton("Shutdown Server");
		backupButton = new JButton("Backup Database");
		
		//Initializes Pane
		bigField = new JTextArea(33,30);
		userList = new JTextArea(15,15);
		lobbyList = new JTextArea(15,15);
		
		//JTextArea Attributes
		bigField.setEditable(false);
		bigField.setLineWrap(true);
		bigField.setWrapStyleWord(true);
		
		userList.setEditable(false);
		userList.setLineWrap(true);
		userList.setWrapStyleWord(true);

		//Add to Panel1
		panel1.add(bigField);
		panel2.add(startButton);
		panel2.add(stopButton);
		panel2.add(backupButton);
		panel3.add(userList);
		panel3.add(lobbyList);

		//Adds the panels to the Main Panel
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.add(panel2, BorderLayout.SOUTH);
		mainPanel.add(userScrollPane, BorderLayout.EAST);

	}

	/**
	 * This method sets the action listener for the Start Server button
	 * @param e the action listener to set.
	 */
	protected void startButtonListener(ActionListener e) {
		startButton.addActionListener(e);
	}

	/**
	 * This method sets the action listener for the Stop Server button
	 * @param e the action listener to set.
	 */
	protected void stopButtonListener(ActionListener e) {
		stopButton.addActionListener(e);
	}

	/**
	 * This method sets the action listener for the Shutdown Server button
	 * @param e the action listener to set.
	 */
	protected void backupButtonListener(ActionListener e) {
		backupButton.addActionListener(e);
	}

	/**
	 * This method sends values to the JTextArea housing all of
	 * the information incoming from the server.
	 * 
	 * @param incomingText text from the server to be appended to
	 *        the JTextArea.
	 */
	protected void incomingInfo(String incomingText) {
		bigField.append(incomingText + "\n");
	}
	
	/**
	 * This method displays the given list of server connections to 
	 * the Server's GUI and their associated player if any. 
	 * @param uList the list to display. 
	 */
	public void updateUserList(ArrayList<SerCon> uList) {
		userList.setText("");
		lobbyList.setText("");
		
		for(int i = 0;i < uList.size();i++) {
			userList.append(uList.get(i).getIPP() + "\n");
			
			if(uList.get(i).getPlayer() != null) {
				lobbyList.append(uList.get(i).getPlayer().getUsername() + "\n");
			}
			else {
				lobbyList.append("\n");
			}
		}
		
	}
}




