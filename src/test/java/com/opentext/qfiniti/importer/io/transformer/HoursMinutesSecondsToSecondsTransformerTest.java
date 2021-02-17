package com.opentext.qfiniti.importer.io.transformer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.opentext.qfiniti.importer.io.transformer.HoursMinutesSecondsToSecondsTransformer;

public class HoursMinutesSecondsToSecondsTransformerTest {

	@Test
	public void transform() {
		HoursMinutesSecondsToSecondsTransformer adapter = new HoursMinutesSecondsToSecondsTransformer();

		String seconds = adapter.transform("00:10:10");
		assertEquals("610", seconds);

		seconds = adapter.transform("10:00:10");
		assertEquals("36010", seconds);

	}
}
