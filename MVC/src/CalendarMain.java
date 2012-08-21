/*
  MVC Pattern
  Sources consulted: 
	http://www.leepoint.net/notes-java/GUI/structure/40mvc.html
	http://www.oracle.com/technetwork/articles/javase/index-142890.html
  Program description: This program runs a Model View Controller with 2 different views. 
  	The main method takes all of the components needed to get the MVC working, and once
  	executed, will take care of everything. 
  	
  	This particular program works with the Date and Time, and allows the user to
  	manipulate one view with live changes to the other to demonstrate the MVC
  	architecture. 
 */

/**
 * CalendarMain is the main class that puts the
 * Model-View-Controller together, and executes it. 
 */
public class CalendarMain {


	public static void main(String[] args) {

		//Creating the Model.
		CalendarModel x = new CalendarModel();

		//Creating View 1.
		CalView1 v1 = new CalView1();
		//Creating Controller 2 for View 2, and passing in the Model and View 2.
		V1Controller c1 = new V1Controller(x,v1);

		//Creating View 2.
		CalView2 v2 = new CalView2();
		//Creating Controller 2 for View 2, and passing in the Model and View 2.
		V2Controller c2 = new V2Controller(x,v2);

		//Adding Controller 1 to the Model.
		x.addController(c1);
		//Adding Controller 2 to the MOdel
		x.addController(c2);


	}

}
