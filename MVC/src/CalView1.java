import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 * 
 * @author Nate Jackson
 *
 * CalView1 is a GUI class for the model view controller. This is
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
public class CalView1 extends JFrame {

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

	//Text Fields
	private JTextField tfYear;
	private JTextField tfDay;
	private JTextField tfMonth;
	private JTextField tfHour;
	private JTextField tfMinute;
	private JTextField tfSecond;

	//Window Size
	private final int W_WIDTH = 300;
	private final int W_HEIGHT = 100;


	/**
	 * CalView1 is the class that builds up the GUI, as well as displays it.
	 * 
	 */
	public CalView1(){
		//cMod = newMod;

		setTitle("Calendar Model 1");
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
		//Initializes the model
		//cMod.initDefault();

		// Initializes the Labels
		monthLabel = new JLabel("Date: ");
		dayLabel = new JLabel("/");
		yearLabel = new JLabel("/");

		//Initializes the TextFields
		tfYear = new JTextField(3);
		tfMonth = new JTextField(2);
		tfDay = new JTextField(2);

		//Initializes the Panels and Layout
		panel1 = new JPanel();
		panel2 = new JPanel();
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		//Adds everything to the Date panel
		panel1.add(monthLabel);
		panel1.add(tfMonth);
		panel1.add(dayLabel);
		panel1.add(tfDay);
		panel1.add(yearLabel);
		panel1.add(tfYear);

		//Time-------------------------
		//Initialize Labels
		hourLabel = new JLabel("Time: ");
		minLabel = new JLabel(":");
		secLabel = new JLabel(":");

		//Initialize Text Fields
		tfHour = new JTextField(2);
		tfMinute = new JTextField(2);
		tfSecond = new JTextField(2);

		//Adds everything to the Time panel
		panel2.add(hourLabel);
		panel2.add(tfHour);
		panel2.add(minLabel);
		panel2.add(tfMinute);
		panel2.add(secLabel);
		panel2.add(tfSecond);

		//Put Together
		mainPanel.add(panel1, BorderLayout.NORTH);
		mainPanel.add(panel2, BorderLayout.CENTER);
	}

	//Years
	/**
	 * This method gets the user input for years.
	 */
	protected String getNewYear() {
		return tfYear.getText();
	}
	/**
	 * This method sets the years value in the text field.
	 * @param daYear the new year being input.
	 */
	protected void setNewYear(String daYear) {
		tfYear.setText(daYear);  
	}
	/**
	 * This method takes in an action listener, and makes that
	 * the action listener for the text field.
	 * 
	 * @param e an input ActionListener
	 */
	protected void addYearListener(ActionListener e) {
		tfYear.addActionListener(e);
	}

	//Months
	/**
	 * This method gets the user input for month.
	 */
	protected String getNewMonth() {
		return tfMonth.getText();
	}
	/**
	 * This method sets the month value in the text field.
	 * @param daMonth the new month being input.
	 */
	protected void setNewMonth(String daMonth) {
		tfMonth.setText(daMonth);

	}
	/**
	 * This method takes in an action listener, and makes that
	 * the action listener for the text field.
	 * 
	 * @param e an input ActionListener
	 */
	protected void addMonthListener(ActionListener e) {
		tfMonth.addActionListener(e);
	}

	//Days
	/**
	 * This method gets the user input for day.
	 */
	protected String getNewDay() {
		return tfDay.getText();
	}
	/**
	 * This method sets the day value in the text field.
	 * @param daDay the new day being input.
	 */
	protected void setNewDay(String daDay) {
		tfDay.setText(daDay);

	}
	/**
	 * This method takes in an action listener, and makes that
	 * the action listener for the text field.
	 * 
	 * @param e an input ActionListener
	 */
	protected void addDayListener(ActionListener e) {
		tfDay.addActionListener(e);
	}

	//Hours
	/**
	 * This method gets the user input for hours.
	 */
	protected String getNewHour() {
		return tfHour.getText();
	}
	/**
	 * This method sets the hour value in the text field.
	 * @param daHour the new hour being input.
	 */
	protected void setNewHour(String daHour) {
		tfHour.setText(daHour);

	}
	/**
	 * This method takes in an action listener, and makes that
	 * the action listener for the text field.
	 * 
	 * @param e an input ActionListener
	 */
	protected void addHourListener(ActionListener e) {
		tfHour.addActionListener(e);
	}

	//Minutes
	/**
	 * This method gets the user input for minutes.
	 */
	protected String getNewMinute() {
		return tfMinute.getText();
	}
	/**
	 * This method sets the minute value in the text field.
	 * @param daMinute the new minutes being input.
	 */
	protected void setNewMinute(String daMinute) {
		tfMinute.setText(daMinute);

	}
	/**
	 * This method takes in an action listener, and makes that
	 * the action listener for the text field.
	 * 
	 * @param e an input ActionListener
	 */
	protected void addMinuteListener(ActionListener e) {
		tfMinute.addActionListener(e);
	}

	//Seconds
	/**
	 * This method gets the user input for seconds.
	 */
	protected String getNewSecond() {
		return tfSecond.getText();
	}
	/**
	 * This method sets the seconds value in the text field.
	 * @param daSecond the new seconds being input.
	 */
	protected void setNewSecond(String daSecond) {
		tfSecond.setText(daSecond);

	}
	/**
	 * This method takes in an action listener, and makes that
	 * the action listener for the text field.
	 * 
	 * @param e an input ActionListener
	 */
	protected void addSecondListener(ActionListener e) {
		tfSecond.addActionListener(e);
	}



}
