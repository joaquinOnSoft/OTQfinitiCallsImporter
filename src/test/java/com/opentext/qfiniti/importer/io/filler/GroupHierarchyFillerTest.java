package com.opentext.qfiniti.importer.io.filler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class GroupHierarchyFillerTest {

	@Test
	public void testGetValue() {
		GroupHierarchyFiller filler = new GroupHierarchyFiller();
		String value = filler.getValue();
		
		assertNotNull(value);
		assertEquals("Client-i", value);
	}
}
