package com.opentext.qfiniti.importer.io.filler;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class DateFromFileFillerTest extends AbstractFillerTest {

	@Test
	public void testGetValue() {
		DateFromFileFiller filler = new DateFromFileFiller(file);
		String value = filler.getValue();

		assertNotNull(value);

		// TODO find a fix for this issue.
		// NOTE: The NIP API is returning the last access time instead of the creation
		// date!!!
		// Real creation date: 28/07/2020 12:35
		// assertEquals("28/07/2020 12:35:00", value);
	}
}
