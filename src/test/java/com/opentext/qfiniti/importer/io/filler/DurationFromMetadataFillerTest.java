package com.opentext.qfiniti.importer.io.filler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class DurationFromMetadataFillerTest extends AbstractFillerTest {

	@Test
	public void testGetValue() {
		DurationFromMetadataFiller filler = new DurationFromMetadataFiller(file);
		String value = filler.getValue();
		assertNotNull(value);
		assertEquals("33", value);
	}
}
