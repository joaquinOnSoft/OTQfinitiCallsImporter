package com.opentext.qfiniti.importer.io.transformer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateMMddyyyyhhmmssaTransformer implements ITransformer {

	private static final String DATE_FORMAT_MM_DD_YYYY_HH_MM_SS_A = "MM/dd/yyyy hh:mm:ss a";
	private static final String DATE_FORMAT_QFINITI = "dd/MM/yyyy HH:mm:ss";
	
	@Override
	public String transform(String strDate) {
		LocalDateTime date =null;
		
		if(strDate != null) {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_MM_DD_YYYY_HH_MM_SS_A, Locale.ENGLISH);
				date = LocalDateTime.parse(strDate, formatter);
			} catch (IllegalArgumentException e) {
				System.err.println(e.getLocalizedMessage());
				return null;
			} 	
		}
		
		DateTimeFormatter qfinitiFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_QFINITI, Locale.ENGLISH);
		return qfinitiFormatter.format(date);
	}

}
