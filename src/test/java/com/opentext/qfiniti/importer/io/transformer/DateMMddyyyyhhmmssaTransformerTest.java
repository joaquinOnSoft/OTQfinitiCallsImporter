package com.opentext.qfiniti.importer.io.transformer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class DateMMddyyyyhhmmssaTransformerTest {

	@Test
	public void transform() {
		DateMMddyyyyhhmmssaTransformer transformer = new DateMMddyyyyhhmmssaTransformer();

		String date = transformer.transform("10/21/2019 11:59:20 AM");
		assertNotNull(date);
		assertEquals("21/10/2019 11:59:20", date);

		date = transformer.transform("10/21/2019 11:59:20 PM");
		assertNotNull(date);
		assertEquals("21/10/2019 23:59:20", date);
	}
}
