package com.opentext.qfiniti.importer.io.filler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

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
