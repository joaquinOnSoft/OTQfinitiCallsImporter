package com.opentext.qfiniti.importer.io.filler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class DateFromFileNameExtXXXXX_MM_dd_yyyy_HH_mm_ssFillerTest extends AbstractFillerTest {
	protected String getFilePath() {
		return "client-q/ext42094_01_14_2016_16;29;15.wav";
	}
	
	@Test
	public void testGetValue() {	
		
		DateFromFileNameExtXXXXX_MM_dd_yyyy_HH_mm_ssFiller filler = new DateFromFileNameExtXXXXX_MM_dd_yyyy_HH_mm_ssFiller(file);
		String value = filler.getValue();
		
		assertNotNull(value);
		assertEquals("14/01/2016 16:29:15", value);
	}
}
