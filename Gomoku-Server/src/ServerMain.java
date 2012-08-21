
//    Sources: Java API w/ JDK

/**
 * 
 * This class is the main class that puts the Gomoku-Server together, and executes
 * it. 
 * 
 * @author Nate Jackson
 */
public class ServerMain {
	
	public static void main(String[] args) {
		//ServerModel
		
		//Creates the View
		ServerGUI theView = new ServerGUI();
		//Creates the Model
		ServerModel model = new ServerModel();
		
		//Creates the Controller
		ServerController server = new ServerController(model, theView);
		
		//Puts the Controller in the model.
		model.connectController(server);
		
		
		
	}

}
