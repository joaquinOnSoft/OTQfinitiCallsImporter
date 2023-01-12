package com.opentext.qfiniti.importer.io.filler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class DateFromFileNameExtXXXXX_MM_dd_yyyy_HH_mm_ssFillerTest extends AbstractFillerTest {
	@Override
	protected String getFilePath() {
		return "client-q/ext42094_01_14_2016_16;29;15.wav";
	}

	@Test
	public void testGetValue() {

		DateFromFileNameExtXXXXX_MM_dd_yyyy_HH_mm_ssFiller filler = new DateFromFileNameExtXXXXX_MM_dd_yyyy_HH_mm_ssFiller(
				call, file);
		String value = filler.getValue();

		assertNotNull(value);
		assertEquals("01/14/2016 16:29:15", value);
	}
}
