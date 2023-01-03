package com.opentext.qfiniti.importer.io.filler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class TeamMemberFromSGTeamFillerTest extends AbstractFillerTest {

	@Test
	public void testGetValue() {
		TeamMemberFromSGTeamFiller filler = new TeamMemberFromSGTeamFiller(call, file);
		String value = filler.getValue();

		assertNotNull(value);
		assertEquals("VS-TI-FL-Team36, VFS_TI_MV_RIC", value);
	}
}
