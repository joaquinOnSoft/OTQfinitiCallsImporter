package com.opentext.qfiniti.importer.io.filler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
