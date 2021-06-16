package com.opentext.qfiniti.importer.io.transformer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractDateTransformer implements ITransformer{

	protected static final Logger log = LogManager.getLogger(AbstractDateTransformer.class);
	protected static final String DATE_FORMAT_QFINITI = "dd/MM/yyyy HH:mm:ss";

	public AbstractDateTransformer() {
		super();
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