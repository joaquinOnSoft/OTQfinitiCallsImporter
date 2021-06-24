/*
 *   (C) Copyright 2019 OpenText and others.
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
package com.opentext.qfiniti.importer.io.transformer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class DateMMddyyyyhhmmTransformer extends AbstractTransformer {

	// Date format example: 04/05/2021 21:54
	private static final String DATE_FORMAT_MM_DD_YYYY_HH_MM = "MM/dd/uuuu HH:mm";

	public DateMMddyyyyhhmmTransformer(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Transforms a date from 'MM/dd/yyyy hh:mm' to format 'dd/MM/yyyy HH:mm:ss'
	 * 
	 * @param strDate - date expressed in format "MM/dd/yyyy hh:mm"
	 * @return date in format 'dd/MM/yyyy HH:mm:ss'
	 * @see https://www.programmersought.com/article/83121948467/
	 */
	@Override
	public String transform(String strDate) {
		LocalDateTime date = null;

		if (strDate != null) {
			try {
				DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern(DATE_FORMAT_MM_DD_YYYY_HH_MM)
			            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
			            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
			            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
			            .toFormatter();
				date = LocalDateTime.parse(strDate, formatter);
			} catch (DateTimeParseException e) {
				log.error(e.getLocalizedMessage());
				return null;
			} catch (IllegalArgumentException e) {
				log.error(e.getLocalizedMessage());
				return null;
			}
		}

		DateTimeFormatter qfinitiFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_QFINITI, Locale.ENGLISH);
		return qfinitiFormatter.format(date);
	}
}
