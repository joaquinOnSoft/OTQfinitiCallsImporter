package com.opentext.qfiniti.importer.io.filler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class DateFromFileFillerTest extends AbstractFillerTest {

	@Test
	public void testGetValue() {
		DateFromFileFiller filler = new DateFromFileFiller(file);
		String value = filler.getValue();
		
		assertNotNull(value);
		//assertEquals("12/08/2020 13:09:06", value);
	}
}
