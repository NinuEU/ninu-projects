import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * 
 * @author Nate Jackson
 *
 * V2Controller is the controller class for CalView2. This
 * controller takes all of the events and actions of CalView2,
 * and makes the necessary changes to the view and the model. This 
 * acts as the central hub between the view and the model. This class
 * also extends CalController, a generic class that all controllers
 * wanting to use the model must extend. 
 *
 */
public class V2Controller extends CalController{

	private CalendarModel cMod; //CalendarModel
	private CalView2 theView;
	
	/**
	 * V2Controller is the constructor for the class that takes in
	 * the CalView2 and CalendarModel, and instantiates the listeners
	 * for the view.
	 * 
	 * @param newModel CalendarModel input model
	 * @param newView CalView2 input view
	 */
	public V2Controller(CalendarModel newModel, CalView2 newView) {
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
	 * Creates the change listener for the year value in
	 * the view. It this one of many soul interactions of the
	 * controller class between the view and the model.
	 *
	 */
	class yearListener implements ChangeListener {

		String userInput = "";
		public void stateChanged(ChangeEvent e) {

			userInput = theView.getNewYear();
			try {
				cMod.setYear(Integer.parseInt(userInput));
			} catch (NumberFormatException l) {
				System.out.println("Illegal Input");
			}
			String inYear = "" + cMod.getYear();
			theView.setNewYear(inYear);

		} 
	}

	/**
	 * Creates the change listener for the month value in
	 * the view. It this one of many soul interactions of the
	 * controller class between the view and the model.
	 *
	 */
	class monthListener implements ChangeListener {

		String userInput = "";
		public void stateChanged(ChangeEvent e) {
			userInput = theView.getNewMonth();
			try {
				cMod.setMonth(Integer.parseInt(userInput));
			} catch (NumberFormatException l) {
				System.out.println("Illegal Input");
			}
			String inMonth = "" + (cMod.getMonth());
			theView.setNewMonth(inMonth);
		} 
	}

	/**
	 * Creates the change listener for the day value in
	 * the view. It this one of many soul interactions of the
	 * controller class between the view and the model.
	 *
	 */
	class dayListener implements ChangeListener {

		String userInput = "";
		public void stateChanged(ChangeEvent e) {
			userInput = theView.getNewDay();
			try {
				cMod.setDay(Integer.parseInt(userInput));
			} catch (NumberFormatException l) {
				System.out.println("Illegal Input");
			}
			String inDay = "" + (cMod.getDay());
			theView.setNewDay(inDay);
		} 
	}

	/**
	 * Creates the change listener for the hour value in
	 * the view. It this one of many soul interactions of the
	 * controller class between the view and the model.
	 *
	 */
	class hourListener implements ChangeListener {

		String userInput = "";
		public void stateChanged(ChangeEvent e) {
			userInput = theView.getNewHour();
			try {
				cMod.setHour(Integer.parseInt(userInput));
			} catch (NumberFormatException l) {
				System.out.println("Illegal Input");
			}
			String inHour = "" + (cMod.getHour());
			theView.setNewHour(inHour);
		} 
	}

	/**
	 * Creates the change listener for the minute value in
	 * the view. It this one of many soul interactions of the
	 * controller class between the view and the model.
	 *
	 */
	class minuteListener implements ChangeListener {

		String userInput = "";
		public void stateChanged(ChangeEvent e) {
			userInput = theView.getNewMinute();
			try {
				cMod.setMinute(Integer.parseInt(userInput));
			} catch (NumberFormatException l) {
				System.out.println("Illegal Input");
			}
			String inMin = "" + (cMod.getMinute());
			theView.setNewMinute(inMin);
		} 
	}

	/**
	 * Creates the change listener for the second value in
	 * the view. It this one of many soul interactions of the
	 * controller class between the view and the model.
	 *
	 */
	class secondListener implements ChangeListener {

		String userInput = "";
		public void stateChanged(ChangeEvent e) {
			userInput = theView.getNewSecond();
			try {
				cMod.setSecond(Integer.parseInt(userInput));
			} catch (NumberFormatException l) {
				System.out.println("Illegal Input");
			}
			String inSec = "" + (cMod.getSecond());
			theView.setNewSecond(inSec);
		} 
	}
}
