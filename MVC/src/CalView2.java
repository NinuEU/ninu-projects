import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.ChangeListener;

/**
 * 
 * @author Nate Jackson
 *
 * CalView2 is a GUI class for the model view controller. This is
 * what the user interacts with. The interactions from here are 
 * examined not in this class, but in the controller, and all the 
 * changes made from this class are from the controller as well.
 * 
 *  ***Updated 3/1/2011***
 *  -Changed the Constructor so you don't have to add in the Model
 *   to retrieve and update information in the view. This is taken care
 *   of by an update in the Model that updates the Controller of the View
 *   once added to the Models list of Controllers.
 * 
 *
 */
public class CalView2 extends JFrame {

	//Panels
	private JPanel mainPanel;
	private JPanel panel1;
	private JPanel panel2;

	//Labels
	private JLabel yearLabel;
	private JLabel monthLabel;
	private JLabel dayLabel;
	private JLabel hourLabel;
	private JLabel minLabel;
	private JLabel secLabel;

	//Sliders
	private JSlider yearSlider;
	private JSlider daySlider;
	private JSlider monthSlider;
	private JSlider hourSlider;
	private JSlider minSlider;
	private JSlider secSlider;

	//Window Size
	private final int W_WIDTH = 800;
	private final int W_HEIGHT = 140;
	
	//CalendarModel

	/**
	 * CalView1 is the class that builds up the GUI, as well as displays it.
	 * 
	 */
	public CalView2(){

		setTitle("Calendar Model 2");
		setSize(W_WIDTH,W_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		buildPanel();

		add(mainPanel);

		setVisible(true);
		//pack();

	}

	/**
	 * buildPanel is a method builds the GUI
	 */
	private void buildPanel() {
		
		//Date-----------------

		//Initializes Labels
		monthLabel = new JLabel("Month");
		dayLabel = new JLabel("Day");
		yearLabel = new JLabel("Year");

		//Initializes Sliders
		monthSlider = new JSlider(JSlider.HORIZONTAL, 1, 12, 1);
		daySlider = new JSlider(JSlider.HORIZONTAL, 1, 31, 1);
		yearSlider = new JSlider(JSlider.HORIZONTAL, 1, 3000, 1);

		//Initializes the Panels and Layout
		panel1 = new JPanel();
		panel2 = new JPanel();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		//Adds everything to the Date panel
		panel1.add(monthLabel);
		panel1.add(monthSlider);
		panel1.add(dayLabel);
		panel1.add(daySlider);
		panel1.add(yearLabel);
		panel1.add(yearSlider);

		//Time-------------------------
		//Initialize Labels
		hourLabel = new JLabel("Hour");
		minLabel = new JLabel("Minute");
		secLabel = new JLabel("Second");

		//Initializes Sliders
		hourSlider = new JSlider(JSlider.HORIZONTAL, 0, 11, 0);
		minSlider = new JSlider(JSlider.HORIZONTAL, 0, 59, 0);
		secSlider = new JSlider(JSlider.HORIZONTAL, 0, 59, 0);

		//Adds everything to the Date panel
		panel2.add(hourLabel);
		panel2.add(hourSlider);
		panel2.add(minLabel);
		panel2.add(minSlider);
		panel2.add(secLabel);
		panel2.add(secSlider);

		//Put Together
		mainPanel.add(panel1, BorderLayout.NORTH);
		mainPanel.add(panel2, BorderLayout.CENTER);
	}

	//Years
	/**
	 * This method gets the user input for year.
	 */
	protected String getNewYear() {
		return "" + yearSlider.getValue();
	}
	/**
	 * This method sets the year value in the slider.
	 * @param daYear the new year being input.
	 */
	protected void setNewYear(String daYear) {
		try {
			yearSlider.setValue(Integer.parseInt(daYear));
		} catch (NumberFormatException e) {
			System.err.println("Illegal Value");
		}  
	}
	/**
	 * This method takes in an change listener, and makes that
	 * the change listener for the slider.
	 * 
	 * @param e an input ChangeListener
	 */
	protected void addYearListener(ChangeListener e) {
		yearSlider.addChangeListener(e);
	}

	//Months
	/**
	 * This method gets the user input for month.
	 */
	protected String getNewMonth() {
		return "" + monthSlider.getValue();
	}
	/**
	 * This method sets the month value in the slider.
	 * @param daMonth the new month being input.
	 */
	protected void setNewMonth(String daMonth) {
		try {
			monthSlider.setValue(Integer.parseInt(daMonth));
		} catch (Exception e) {
			System.err.println("Illegal Value");
		}

	}
	/**
	 * This method takes in an change listener, and makes that
	 * the change listener for the slider.
	 * 
	 * @param e an input ChangeListener
	 */
	protected void addMonthListener(ChangeListener e) {
		monthSlider.addChangeListener(e);
	}

	//Days
	/**
	 * This method gets the user input for day.
	 */
	protected String getNewDay() {
		return "" + daySlider.getValue();
	}
	/**
	 * This method sets the day value in the slider.
	 * @param daDay the new day being input.
	 */
	protected void setNewDay(String daDay) {
		try {
			daySlider.setValue(Integer.parseInt(daDay));
		} catch (NumberFormatException e) {
			System.err.println("Illegal Value");
		}

	}
	/**
	 * This method takes in an change listener, and makes that
	 * the change listener for the slider.
	 * 
	 * @param e an input ChangeListener
	 */
	protected void addDayListener(ChangeListener e) {
		daySlider.addChangeListener(e);
	}

	//Hours
	/**
	 * This method gets the user input for hours.
	 */
	protected String getNewHour() {
		return "" + hourSlider.getValue();
	}
	/**
	 * This method sets the hour value in the slider.
	 * @param daHour the new hour being input.
	 */
	protected void setNewHour(String daHour) {
		try {
			hourSlider.setValue(Integer.parseInt(daHour));
		} catch (NumberFormatException e) {
			System.err.println("Illegal Value");
		}

	}
	/**
	 * This method takes in an change listener, and makes that
	 * the change listener for the slider.
	 * 
	 * @param e an input ChangeListener
	 */
	protected void addHourListener(ChangeListener e) {
		hourSlider.addChangeListener(e);
	}


	//Minutes
	/**
	 * This method gets the user input for minutes.
	 */
	protected String getNewMinute() {
		return "" + minSlider.getValue();
	}
	/**
	 * This method sets the minutes value in the slider.
	 * @param daMinute the new minutes being input.
	 */
	protected void setNewMinute(String daMinute) {
		try {
			minSlider.setValue(Integer.parseInt(daMinute));
		} catch (NumberFormatException e) {
			System.err.println("Illegal Value");
		}

	}
	/**
	 * This method takes in an change listener, and makes that
	 * the change listener for the slider.
	 * 
	 * @param e an input ChangeListener
	 */
	protected void addMinuteListener(ChangeListener e) {
		minSlider.addChangeListener(e);
	}

	//Seconds
	/**
	 * This method gets the user input for seconds.
	 */
	protected String getNewSecond() {
		return "" + secSlider.getValue();
	}
	/**
	 * This method sets the seconds value in the slider.
	 * @param daSecond the new seconds being input.
	 */
	protected void setNewSecond(String daSecond) {
		try {
			secSlider.setValue(Integer.parseInt(daSecond));
		} catch (NumberFormatException e) {
			System.err.println("Illegal Value");
		}

	}
	/**
	 * This method takes in an change listener, and makes that
	 * the change listener for the slider.
	 * 
	 * @param e an input ChangeListener
	 */
	protected void addSecondListener(ChangeListener e) {
		secSlider.addChangeListener(e);
	}



}
