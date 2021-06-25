package com.opentext.qfiniti.importer.io.filler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class AniFillerTest extends AbstractFillerTest {

	@Test
	public void testGetValue() {
		AniFiller filler = new AniFiller(call, file);
		String value = filler.getValue();

		assertNotNull(value);
		assertEquals(9, value.length());
		assertEquals('6', value.charAt(0));
	}
}
