package com.opentext.qfiniti.importer.io.filler;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GroupHierarchyDolceVitaFillerTest extends AbstractFillerTest {

	@Test
	public void testGetValue() {
		GroupHierarchyDolceVitaFiller filler = new GroupHierarchyDolceVitaFiller(call, file);
		String value = filler.getValue();

		assertNotNull(value);
		assertTrue(value.startsWith("VS-TI-FL-Team"));
	}
}
