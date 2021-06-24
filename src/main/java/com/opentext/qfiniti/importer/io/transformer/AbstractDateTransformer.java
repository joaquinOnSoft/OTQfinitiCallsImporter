package com.opentext.qfiniti.importer.io.transformer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public abstract class AbstractDateTransformer extends AbstractTransformer{

	public AbstractDateTransformer(String path) {
		super(path);
	}

	/**
	 * Transforms a date from given format to format 'dd/MM/yyyy HH:mm:ss' (Qfiniti format)
	 * 
	 * @param strDate - date expressed in a given format
	 * @param strDateFormat - date format, i.e. 'MM/dd/yyyy hh:mm:ss a'  
	 * @return date in format 'dd/MM/yyyy HH:mm:ss'
	 */
	public String transform(String strDate, String strDateFormat) {
		LocalDateTime date = null;

		if (strDate != null) {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(strDateFormat, Locale.ENGLISH);
				date = LocalDateTime.parse(strDate, formatter);
			} 
			catch (DateTimeParseException  e) {
				log.error(e.getLocalizedMessage());
				return null;
			} 
			catch (IllegalArgumentException e) {
				log.error(e.getLocalizedMessage());
				return null;
			}
		}

		DateTimeFormatter qfinitiFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_QFINITI, Locale.ENGLISH);
		return qfinitiFormatter.format(date);
	}
	
	
}