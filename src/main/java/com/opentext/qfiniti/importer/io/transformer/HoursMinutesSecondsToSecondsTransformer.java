package com.opentext.qfiniti.importer.io.transformer;

public class HoursMinutesSecondsToSecondsTransformer implements ITransformer{
	
	private static final int SECONDS_IN_A_MINUTE = 60;
	private static final int SECONDS_IN_AN_HOUR = 3600;
	
	
	public static final int INDEX_HOURS = 0;
	public static final int INDEX_MINUTES = 1;
	public static final int INDEX_SECONDS = 2;	
	
	@Override
	public String transform(String hms) {
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
		
		return Integer.toString(totalSeconds);
	}
}
