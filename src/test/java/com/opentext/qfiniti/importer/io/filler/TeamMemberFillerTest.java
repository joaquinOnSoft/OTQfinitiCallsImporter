package com.opentext.qfiniti.importer.io.filler;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TeamMemberFillerTest extends AbstractFillerTest {

	@Test
	public void testGetValue() {
		TeamMemberFiller filler = new TeamMemberFiller(call, file);
		String value = filler.getValue();

		assertNotNull(value);
		assertTrue(value.contains(","));
	}
}
