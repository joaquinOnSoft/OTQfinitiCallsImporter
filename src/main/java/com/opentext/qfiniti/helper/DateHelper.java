package com.opentext.qfiniti.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
	public static final String DATE_FORMAT_IBERDROLA = "MM/dd/yyyy hh:mm:ss a";
	
	private static final int SECONDS_IN_A_MINUTE = 60;
	private static final int SECONDS_IN_AN_HOUR = 3600;
	
	
	public static final int INDEX_HOURS = 0;
	public static final int INDEX_MINUTES = 1;
	public static final int INDEX_SECONDS = 2;	
	
	/**
	 * Provides the duration in seconds from a duration expressed as "hh:mm:ss"
	 * @param hms - Duration in format "hh:mm:ss" where:
	 * <ul>
	 * 		<li>hh - hours</li>
	 * 		<li>mm - minutes</li>
	 * 		<li>ss - seconds</li>
	 * </ul> 
	 * @return Duration in seconds
	 */
	public static int hoursMinutesSecondsToSeconds(String hms) {
		String hours = "0"; 
		String minutes = "0"; 
		String seconds = "0";
		int totalSeconds = 0;
		
		if(hms != null) {
			String[] parts = hms.split(":");
			if(parts.length == 3) {
				hours = parts[INDEX_HOURS];
				minutes = parts[INDEX_MINUTES];
				seconds = parts[INDEX_SECONDS];
			}
		}
		
		totalSeconds = Integer.parseInt(hours) * SECONDS_IN_AN_HOUR +
				Integer.parseInt(minutes) * SECONDS_IN_A_MINUTE +
				Integer.parseInt(seconds);
		
		return totalSeconds;
	}
	
	public static Date strToDate(String strDate, String format) {
		Date date =null;
		
		if(strDate != null) {
			try {
				date=new SimpleDateFormat(format).parse(strDate);
			} catch (ParseException e) {
				System.err.println(e.getLocalizedMessage());
			} 	
		}
		 
		return date;		
	}
}
