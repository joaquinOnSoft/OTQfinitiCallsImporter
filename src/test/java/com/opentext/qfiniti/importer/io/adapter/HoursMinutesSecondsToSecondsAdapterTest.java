package com.opentext.qfiniti.importer.io.adapter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HoursMinutesSecondsToSecondsAdapterTest {
	
	@Test
	public void transform() {
		HoursMinutesSecondsToSecondsAdapter adapter = new HoursMinutesSecondsToSecondsAdapter();
		
		String seconds = adapter.transform("00:10:10");
		assertEquals("610", seconds);
		
		seconds = adapter.transform("10:00:10");
		assertEquals("36010", seconds);		
		
	}
}
