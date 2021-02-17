package com.opentext.qfiniti.importer.io.transformer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateMMddyyyyhhmmssaTransformer implements ITransformer {

	private static final Logger log = LogManager.getLogger(DateMMddyyyyhhmmssaTransformer.class);

	private static final String DATE_FORMAT_MM_DD_YYYY_HH_MM_SS_A = "MM/dd/yyyy hh:mm:ss a";
	private static final String DATE_FORMAT_QFINITI = "dd/MM/yyyy HH:mm:ss";

	/**
	 * Transforms a date from 'MM/dd/yyyy hh:mm:ss a' to format 'dd/MM/yyyy
	 * HH:mm:ss'
	 * 
	 * @param strDate - date expressed in format 'MM/dd/yyyy hh:mm:ss a'
	 * @return date in format 'dd/MM/yyyy HH:mm:ss'
	 */
	@Override
	public String transform(String strDate) {
		LocalDateTime date = null;

		if (strDate != null) {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_MM_DD_YYYY_HH_MM_SS_A,
						Locale.ENGLISH);
				date = LocalDateTime.parse(strDate, formatter);
			} catch (IllegalArgumentException e) {
				log.error(e.getLocalizedMessage());
				return null;
			}
		}

		DateTimeFormatter qfinitiFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_QFINITI, Locale.ENGLISH);
		return qfinitiFormatter.format(date);
	}

}
