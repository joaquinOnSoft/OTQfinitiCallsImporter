/*
 *   (C) Copyright 2021 OpenText and others.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *   Contributors:
 *     Joaquín Garzón - initial implementation
 *
 */
package com.opentext.qfiniti.importer.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * 
 * @author Joaquín Garzón
 * @since 20.2
 */
public class DateUtil {
	//	non-breaking space	&#160;
	private static final String NON_BREAKING_SPACE = "\u00A0";
	
	/**
	 * Return current time in UTC format, e.g. 2020-05-21T16:30:52.123Z
	 * 
	 * @return current time in UTC format
	 */
	public static String nowToUTC() {
		return Instant.now().toString();
	}

	public static String dateToUTC(Date d) {
		return dateToFormat(d, "yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	public static String dateToQfinitiFormat(Date d) {
		return dateToFormat(d, "dd/MM/yyyy HH:mm:ss");
	}

	public static String dateToQfiniti204Format(Date d) {
		String date = dateToFormat(d, "MM/dd/yyyy hh:mm:ss a");
		// In my laptop return "12/31/2021 02:33:58 P. M."
		// Java format using 'a' is supposed to return
		// 'PM' or 'AM', but is returning 'p. m.' or 'a. m.', 
		// including a non-breaking space (HTML code &#160; or Unicode \u00A0)
		date = date.replaceAll("\\.", "")
				.replace(NON_BREAKING_SPACE, "")
				.toUpperCase();
		return date;
	}
	
	
	public static String dateToFormat(Date d, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(d);
	}

	/**
	 * Generate a Date object from a string in UTC format
	 * 
	 * @param utc - String which contains a date in UTC format, e.g.
	 *            "2020-05-21T16:30:52.123Z"
	 * @return Date object from a string in UTC format
	 * @throws ParseException
	 */
	public static Date utcToDate(String utc) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(utc);
	}

	public static Date strToDate(String strDate, String format) throws ParseException {
		return new SimpleDateFormat(format).parse(strDate);
	}

}
