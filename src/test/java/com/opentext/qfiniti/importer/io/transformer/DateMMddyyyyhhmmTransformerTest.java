package com.opentext.qfiniti.importer.io.transformer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class DateMMddyyyyhhmmTransformerTest {

	@Test
	public void transform() {
		DateMMddyyyyhhmmTransformer transformer = new DateMMddyyyyhhmmTransformer();

		String date = transformer.transform("10/21/2019 11:59");
		assertNotNull(date);
		assertEquals("21/10/2019 11:59:00", date);

		date = transformer.transform("10/21/2019 23:59");
		assertNotNull(date);
		assertEquals("21/10/2019 23:59:00", date);
	}
}
