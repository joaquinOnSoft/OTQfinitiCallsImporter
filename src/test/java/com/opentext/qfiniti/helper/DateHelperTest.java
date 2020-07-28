package com.opentext.qfiniti.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Test;

public class DateHelperTest {
	@Test
	public void strToDate() {
		LocalDate date = DateHelper.strToDate("10/21/2019 11:59:20 PM", DateHelper.DATE_FORMAT_IBERDROLA);
		assertNotNull(date);
		assertEquals("2019-10-21", date.toString());
	}
}
