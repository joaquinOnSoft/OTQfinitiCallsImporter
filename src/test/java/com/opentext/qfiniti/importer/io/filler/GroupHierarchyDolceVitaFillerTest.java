package com.opentext.qfiniti.importer.io.filler;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GroupHierarchyDolceVitaFillerTest extends AbstractFillerTest {

	@Test
	public void testGetValue() {
		GroupHierarchyDolceVitaFiller filler = new GroupHierarchyDolceVitaFiller(file);
		String value = filler.getValue();

		assertNotNull(value);
		assertTrue(value.startsWith("VS-TI-FL-Team"));
	}
}
