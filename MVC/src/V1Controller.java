import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author Nate Jackson
 *
 * V1Controller is the controller class for CalView1. This
 * controller takes all of the events and actions of CalView1,
 * and makes the necessary changes to the view and the model. This 
 * acts as the central hub between the view and the model. This class
 * also extends CalController, a generic class that all controllers
 * wanting to use the model must extend. 
 *
 */

public class V1Controller extends CalController{


	private CalendarModel cMod; 
	private CalView1 theView;

	/**
	 * V1Controller is the constructor for the class that takes in
	 * the CalView1 and CalendarModel, and instantiates the listeners
	 * for the view.
	 * 
	 * @param newModel CalendarModel input model
	 * @param newView CalView1 input view
	 */
	public V1Controller(CalendarModel newModel, CalView1 newView) {
		//Passing
		cMod = newModel;
		//cMod.initDefault();
		theView = newView;

		//Creates Listeners for the Date Information
		theView.addMonthListener(new monthListener());
		theView.addYearListener(new yearListener());
		theView.addDayListener(new dayListener());

		//Creates Listeners for the Time Information
		theView.addHourListener(new hourListener());
		theView.addMinuteListener(new minuteListener());
		theView.addSecondListener(new secondListener());
	}

	/**
	 * updateController is an abstract method needed to be implemented by 
	 * all controllers that want use CalendarModel.java as their Model. This
	 * is a method called in CalendarModel that updates 
	 */
	public void updateController() {

		//Update the Year
		String inYear = "" + cMod.getYear();
		theView.setNewYear(inYear);

		//Update the Month
		String inMonth = "" + (cMod.getMonth());
		theView.setNewMonth(inMonth);

		//Update the Day
		String inDay = "" + (cMod.getDay());
		theView.setNewDay(inDay);

		//Update the Hour
		String inHour = "" + (cMod.getHour());
		theView.setNewHour(inHour);

		//Update the Minute
		String inMin = "" + (cMod.getMinute());
		theView.setNewMinute(inMin);

		//Update the Second
		String inSec = "" + (cMod.getSecond());
		theView.setNewSecond(inSec);

	}

	/**
	 * Creates the action listener for the year value in
	 * the view. It this one of many soul interactions of the
	 * controller class between the view and the model.
	 *
	 */
	class yearListener implements ActionListener {

		String userInput = "";
		public void actionPerformed(ActionEvent e) {

			userInput = theView.getNewYear();
			try {
				cMod.setYear(Integer.parseInt(userInput));
			} catch (NumberFormatException l) {
				System.err.println("Illegal Value");
			}
			String inYear = "" + cMod.getYear();
			theView.setNewYear(inYear);

		} 
	}

	/**
	 * Creates the action listener for the month value in
	 * the view. It this one of many soul interactions of the
	 * controller class between the view and the model.
	 *
	 */
	class monthListener implements ActionListener {

		String userInput = "";
		public void actionPerformed(ActionEvent e) {
			userInput = theView.getNewMonth();
			try {
				cMod.setMonth(Integer.parseInt(userInput));
			} catch (NumberFormatException l) {
				System.err.println("Illegal Value");
			}
			String inMonth = "" + (cMod.getMonth());
			theView.setNewMonth(inMonth);
		} 
	}

	/**
	 * Creates the action listener for the day value in
	 * the view. It this one of many soul interactions of the
	 * controller class between the view and the model.
	 *
	 */
	class dayListener implements ActionListener {

		String userInput = "";
		public void actionPerformed(ActionEvent e) {
			userInput = theView.getNewDay();
			try {
				cMod.setDay(Integer.parseInt(userInput));
			} catch (NumberFormatException l) {
				System.err.println("Illegal Value");
			}
			String inDay = "" + (cMod.getDay());
			theView.setNewDay(inDay);
		} 
	}

	/**
	 * Creates the action listener for the hour value in
	 * the view. It this one of many soul interactions of the
	 * controller class between the view and the model.
	 *
	 */
	class hourListener implements ActionListener {

		String userInput = "";
		public void actionPerformed(ActionEvent e) {
			userInput = theView.getNewHour();
			try {
				cMod.setHour(Integer.parseInt(userInput));
			} catch (NumberFormatException l) {
				System.err.println("Illegal Value");
			}
			String inHour = "" + (cMod.getHour());
			theView.setNewHour(inHour);
		} 
	}

	/**
	 * Creates the action listener for the minute value in
	 * the view. It this one of many soul interactions of the
	 * controller class between the view and the model.
	 *
	 */
	class minuteListener implements ActionListener {

		String userInput = "";
		public void actionPerformed(ActionEvent e) {
			userInput = theView.getNewMinute();
			try {
				cMod.setMinute(Integer.parseInt(userInput));
			} catch (NumberFormatException l) {
				System.err.println("Illegal Value");
			}
			String inMin = "" + (cMod.getMinute());
			theView.setNewMinute(inMin);
		} 
	}

	/**
	 * Creates the action listener for the second value in
	 * the view. It this one of many soul interactions of the
	 * controller class between the view and the model.
	 *
	 */
	class secondListener implements ActionListener {

		String userInput = "";
		public void actionPerformed(ActionEvent e) {
			userInput = theView.getNewSecond();
			try {
				cMod.setSecond(Integer.parseInt(userInput));
			} catch (NumberFormatException l) {
				System.err.println("Illegal Value");
			}
			String inSec = "" + (cMod.getSecond());
			theView.setNewSecond(inSec);
		} 
	}
}
