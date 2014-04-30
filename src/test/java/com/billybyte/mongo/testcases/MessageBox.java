package com.billybyte.mongo.testcases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Calendar;


import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.billybyte.commonstaticmethods.Dates;


public class MessageBox {
	public final static String MessageBoxWithChoices(
			JFrame frame,
			String messageToDisplay,
			String title,
			Object[] choices,
			Object defaultValue){
			return 
				(String)JOptionPane.showInputDialog(
						frame,
						messageToDisplay,
						title,
						JOptionPane.OK_OPTION,
						null,
						choices,
						defaultValue.toString());				
		
	}
	
	public final static String MessageBoxNoChoices(
			JFrame frame,
			String messageToDisplay,
			String title,
			Object defaultValue){
			return 
				(String)JOptionPane.showInputDialog(
						frame,
						messageToDisplay,
						title,
						JOptionPane.OK_OPTION,
						null,
						null,
						defaultValue.toString());				
		
	}

	
	public final static int MessageBoxYesNo(
			JFrame frame,
			String messageToDisplay,
			String title){
		return 
		JOptionPane.showConfirmDialog(
				frame,
				messageToDisplay,
				title,
				JOptionPane.YES_NO_OPTION);				
	
	}
	
	
	public final static String ConsoleMessage(String message){
        try {
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
			System.out.print(message);
			return console.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * Post a messageBox with a YYYYMMDD date, and accept a date return
	 * @param businessDayOffset offset from today
	 * @param dateSplitChar like "/" or"-"
	 * @return String hopefully, a date in YYYYMMDD form.
	 */
	public static String messageBoxYyyyMmDd(int businessDayOffset,String dateSplitChar){
		Calendar c = Calendar.getInstance();
		c = Dates.addBusinessDays("US", c, businessDayOffset);
		int year = new Integer(c.get(Calendar.YEAR));
		int month = new Integer(c.get(Calendar.MONTH))+1;
		int day = new Integer(c.get(Calendar.DAY_OF_MONTH));
		DecimalFormat dfYear = new DecimalFormat("0000");
		DecimalFormat dfMonthDay = new DecimalFormat("00");
		
		String defaultValue = ""+dfYear.format(year)+dateSplitChar+dfMonthDay.format(month)+dateSplitChar+dfMonthDay.format(day);
		defaultValue = MessageBox.MessageBoxNoChoices(new JFrame(), "adjust date as neede", "Get Date", defaultValue);
		return defaultValue;
	}

}
