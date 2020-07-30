package com.opentext.qfiniti.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import org.junit.Test;

public class DateHelperTest {
	@Test
	public void strToDate() {
		LocalDateTime date = DateHelper.strToDate("10/21/2019 11:59:20 PM", DateHelper.DATE_FORMAT_IBERDROLA);
		assertNotNull(date);
		assertEquals("2019-10-21T23:59:20", date.toString());
	}
}
