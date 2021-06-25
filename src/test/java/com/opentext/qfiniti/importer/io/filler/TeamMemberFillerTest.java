package com.opentext.qfiniti.importer.io.filler;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TeamMemberFillerTest extends AbstractFillerTest {

	@Test
	public void testGetValue() {
		TeamMemberFiller filler = new TeamMemberFiller(call, file);
		String value = filler.getValue();

		assertNotNull(value);
		assertTrue(value.contains(","));
	}
}
