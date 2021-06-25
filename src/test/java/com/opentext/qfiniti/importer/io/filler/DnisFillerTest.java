package com.opentext.qfiniti.importer.io.filler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DnisFillerTest extends AbstractFillerTest {

	@Test
	public void testGetValue() {
		DnisFiller filler = new DnisFiller(call, file);
		String value = filler.getValue();

		assertNotNull(value);
		assertEquals(4, value.length());
		int iValue = Integer.parseInt(value);
		assertTrue(iValue >= 1000);
		assertTrue(iValue <= 5000);
	}
}
