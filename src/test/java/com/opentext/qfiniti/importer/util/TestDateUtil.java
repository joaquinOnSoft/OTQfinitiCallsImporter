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

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import junit.framework.TestCase;

public class TestDateUtil extends TestCase {

	private int subStringToInt(String str, int init, int end) {
		return Integer.parseInt(str.substring(init, end));
	}

	@Test
	public void testNowToUTC() {
		Calendar now = GregorianCalendar.getInstance();

		String dateStr = DateUtil.nowToUTC();

		assertNotNull(dateStr);
		assertEquals(now.get(Calendar.YEAR), subStringToInt(dateStr, 0, 4));
		assertEquals(now.get(Calendar.MONTH) + 1, subStringToInt(dateStr, 5, 7));
		assertEquals(now.get(Calendar.DAY_OF_MONTH), subStringToInt(dateStr, 8, 10));
	}

	@Test
	public void testUtcToDate() {
		final String utcRef = "2020-05-21T16:30:52Z";

		try {
			Date utc = DateUtil.utcToDate(utcRef);
			assertEquals(utcRef, DateUtil.dateToUTC(utc));
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testDateToUTC() {
		Calendar now = GregorianCalendar.getInstance();
		String nowStr = DateUtil.dateToUTC(now.getTime());

		assertNotNull(nowStr);
		assertEquals(now.get(Calendar.YEAR), subStringToInt(nowStr, 0, 4));
		assertEquals(now.get(Calendar.MONTH) + 1, subStringToInt(nowStr, 5, 7));
		assertEquals(now.get(Calendar.DAY_OF_MONTH), subStringToInt(nowStr, 8, 10));
	}

	@Test
	public void testStrToDate() {
		final String strDate = "01/12/2020";
		final String format = "dd/MM/yyyy";

		try {
			Date date = DateUtil.strToDate(strDate, format);
			assertEquals("Tue Dec 01 00:00:00 CET 2020", date.toString());
		} catch (ParseException e) {
			fail(e.getMessage());
		}
	}

}
