package com.opentext.qfiniti.importer.io.filler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class GroupHierarchyFillerTest extends AbstractFillerTest {

	@Test
	public void testGetValue() {
		GroupHierarchyFiller filler = new GroupHierarchyFiller(call, file);
		String value = filler.getValue();

		assertNotNull(value);
		assertEquals("Client-i", value);
	}
}
