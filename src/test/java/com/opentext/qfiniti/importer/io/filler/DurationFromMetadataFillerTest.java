package com.opentext.qfiniti.importer.io.filler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class DurationFromMetadataFillerTest extends AbstractFillerTest {

	@Test
	public void testGetValue() {
		DurationFromMetadataFiller filler = new DurationFromMetadataFiller(call, file);
		String value = filler.getValue();
		assertNotNull(value);
		assertEquals("33", value);
	}
}
