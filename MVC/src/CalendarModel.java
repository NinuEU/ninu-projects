import java.util.ArrayList;
import java.util.Calendar;


/**
 * @author Nate Jackson
 *
 * CalendarModelis the base model for a Model-View-Controller
 * displaying a Date, and Time. Controllers wanted to be controlled
 * by this must be added to the model in order to grab information. 
 *  
 *  
 *  ***Updated 3/1/2011***
 *  -Added CalendarModel Constructor that initializes that variables with the
 *   initDefault() method rather then having an outside class do it.
 *  -Added updateController() method to addController method so the View
 *   does not have to interact with the Model.
 *   
 *
 */
public class CalendarModel {

	private int month; //Month
	private int day; //Day
	private int year; // Years
	private int hour; //Hours
	private int minute; //Minutes
	private int second; //Seconds
	private Calendar cal; //Calendar 
	private ArrayList<CalController> moop = new ArrayList<CalController>(); //Controller Array

	
	public CalendarModel() {
		initDefault();
	}
	
	/**
	 * initDefault is a class called used to create all of the information
	 * for the model at the given time. It uses the Calendar class to get
	 * information of the current date and time to popular the variables. 
	 * This must be called in order to grab any information from the model.
	 */
	private void initDefault() {
		cal = Calendar.getInstance();
		month = (cal.get(Calendar.MONTH) + 1);
		day = (cal.get(Calendar.DAY_OF_MONTH));
		year = (cal.get(Calendar.YEAR));
		hour = (cal.get(Calendar.HOUR_OF_DAY) % 12);
		minute = (cal.get(Calendar.MINUTE));
		second = (cal.get(Calendar.SECOND));
	}

	/**
	 * getMonth returns the current month value in the 
	 * model.
	 *  
	 * @return the month currently in the model.
	 */
	public int getMonth() {
		return month;

	}

	/**
	 * setMonth sets the month value in the model. When 
	 * the new month is passed, the change is validated 
	 * using the checkDM method. If legal, it will update
	 * the month, and update the controllers. 
	 * 
	 * @param newMonth the new month value to put into the
	 *  model.
	 */
	public void setMonth(int newMonth) {
		if(checkDM(day,newMonth,year)) {
			month = newMonth;
			updateControllers();
		}

	}

	/**
	 * getDay returns the current day value in the 
	 * model.
	 * 
	 * @return the day value currently in the model.
	 */
	public int getDay() {
		return day;
	}

	/**
	 * setDay sets the day value in the model. When 
	 * the new day is passed, the change is validated 
	 * using the checkDM method. If legal, it will update
	 * the day, and update the controllers. 
	 * 
	 * @param newDay the new day value to put into the
	 *  model.
	 */
	public void setDay(int newDay) {
		if(checkDM(newDay, month, year)) {
			day = newDay;
			updateControllers();
		}

	}

	/**
	 * getYear returns the current year value in the 
	 * model.
	 * 
	 * @return the year value currently in the model
	 */
	public int getYear() {
		return year;
	}

	/**
	 * setYear sets the year value in the model. When 
	 * the new year is passed, the change is validated 
	 * using the checkDM method. If legal, it will update
	 * the year, and update the controllers. 
	 *
	 * @param newYear the new year value to put into the
	 *  model
	 */
	public void setYear(int newYear) {
		if(newYear >= 1 && newYear <= 3000) {
			if(checkDM(day, month, newYear)) {
				year = newYear;
				updateControllers();
			}
		}
	}

	/**
	 * getHour returns the current hour value in the 
	 * model
	 *  
	 * @return the hour value currently in the model.
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * setHour sets the hour value in the model. If legal, 
	 * it will update the hour, and update the controllers. 
	 * 
	 * @param newHour the new year value to put into the
	 *  model.
	 */
	public void setHour(int newHour) {
		if(newHour >= 0 && newHour <= 11) {
			hour = newHour;
			updateControllers();
		}

	}

	/**
	 * getMinute returns the current minute value in the 
	 * model.
	 * 
	 * @return the minute value currently in the model.
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * setMinute sets the minute value in the model. If legal, 
	 * it will update the minute, and update the controllers. 
	 *  
	 * @param newMinute the new year value to put into the
	 *  model.
	 */
	public void setMinute(int newMinute) {
		if (newMinute >= 0 && newMinute <= 59) {
			minute = newMinute;
			updateControllers();
		}

	}

	/**
	 * getSecond returns the current minute value in the
	 * model.
	 * 
	 * @return the second value currently in the model
	 */
	public int getSecond() {
		return second;
	}

	/**
	 * setSecond sets the second value in the model. If legal, 
	 * it will update the second, and update the controllers. 
	 * 
	 * @param newSecond the new second value to put into the
	 *  model.
	 */
	public void setSecond(int newSecond) {
		if(newSecond >= 0 && newSecond <= 59) {
			second = newSecond;
			updateControllers();
		}

	}

	/**
	 * checkDM takes in a given day, month, and year, and uses them
	 * to evaluate if the input values are indeed a legal date to use,
	 * or are illegal. They first check the month, then the day, and if
	 * needed, the year.
	 * 
	 * @param inDay the day value to evaluate
	 * @param inMonth the month value to evaluate
	 * @param inYear the year value to evaluate
	 * @return true if the combination of day, month, and year are
	 * 	a legal date.
	 */
	public boolean checkDM(int inDay, int inMonth, int inYear) {

		if(inMonth >= 1 && inMonth <= 12) {
			//January
			if(inMonth == 1) {
				if ((inDay <= 31) && (inDay >= 1)) {
					return true;
				}
				else {
					return false;
				}
			}
			//February
			else if (inMonth == 2) {
				//Common Year
				if (((inDay <= 28) && (inDay >= 1))&& ((inYear % 4) > 0)) {
					return true;
				}
				//Leap Year
				else if ((inDay <= 29) && (inDay >= 1) && ((inYear % 4) == 0)) {
					return true;
				}
				else {
					return false;
				}
			}
			//March
			else if (inMonth == 3) {
				if ((inDay <= 31) && (inDay >= 1)) {
					return true;
				}
				else {
					return false;
				}
			}
			//April
			else if (inMonth == 4) {
				if ((inDay <= 30) && (inDay >= 1)) {
					return true;
				}
				else {
					return false;
				}
			}
			//May
			else if (inMonth == 5) {
				if ((inDay <= 31) && (inDay >= 1)) {
					return true;
				}
				else {
					return false;
				}
			}
			//June
			else if (inMonth == 6) {
				if ((inDay <= 30) && (inDay >= 1)) {
					return true;
				}
				else {
					return false;
				}
			}
			//July
			else if (inMonth == 7) {
				if ((inDay <= 31) && (inDay >= 1)) {
					return true;
				}
				else {
					return false;
				}
			}
			//August
			else if (inMonth == 8) {
				if ((inDay <= 31) && (inDay >= 1)) {
					return true;
				}
				else {
					return false;
				}
			}
			//September
			else if (inMonth == 9) {
				if ((inDay <= 30) && (inDay >= 1)) {
					return true;
				}
				else {
					return false;
				}
			}
			//October
			else if (inMonth == 10) {
				if ((inDay <= 31) && (inDay >= 1)) {
					return true;
				}
				else {
					return false;
				}
			}
			//November
			else if (inMonth == 11) {
				if ((inDay <= 30) && (inDay >= 1)) {
					return true;
				}
				else {
					return false;
				}
			}
			//December
			else if (inMonth == 12) {
				if ((inDay <= 31) && (inDay >= 1)) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}


	}

	/**
	 * addController takes in a controller, and puts
	 * it into an ArrayList to later update controllers
	 * of changes to the model.
	 * 
	 * @param x the controller to be added
	 */
	public void addController(CalController x) {
		moop.add(x);
		x.updateController();
	}

	/**
	 * updateController goes through all the controllers
	 * added to the model, and sends an update message to 
	 * them letting them know that the model has been updated.
	 * 
	 */
	private void updateControllers() {
		for(int i = 0;i < moop.size();i++) {
			moop.get(i).updateController();
		}
	}

}
