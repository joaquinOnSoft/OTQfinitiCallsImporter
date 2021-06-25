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

public class HoursMinutesSecondsToSecondsTransformer extends AbstractTransformer {

	private static final int SECONDS_IN_A_MINUTE = 60;
	private static final int SECONDS_IN_AN_HOUR = 3600;

	public static final int INDEX_HOURS = 0;
	public static final int INDEX_MINUTES = 1;
	public static final int INDEX_SECONDS = 2;

	public HoursMinutesSecondsToSecondsTransformer(String path) {
		super(path);
	}	
	
	/**
	 * Transforms a duration expressed as hh:mm:ss to a number of seconds
	 * 
	 * @param duration expressed as hh:mm:ss
	 * @return duration expressed in seconds
	 */
	@Override
	public String transform(String hms) {
		String hours = "0";
		String minutes = "0";
		String seconds = "0";
		int totalSeconds = 0;

		if (hms != null) {
			String[] parts = hms.split(":");
			if (parts.length == 3) {
				hours = parts[INDEX_HOURS];
				minutes = parts[INDEX_MINUTES];
				seconds = parts[INDEX_SECONDS];
			}
		}

		totalSeconds = Integer.parseInt(hours) * SECONDS_IN_AN_HOUR + Integer.parseInt(minutes) * SECONDS_IN_A_MINUTE
				+ Integer.parseInt(seconds);

		return Integer.toString(totalSeconds);
	}
}
