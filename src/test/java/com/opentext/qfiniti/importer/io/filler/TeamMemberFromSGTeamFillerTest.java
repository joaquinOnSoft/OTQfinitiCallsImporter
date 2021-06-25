package com.opentext.qfiniti.importer.io.filler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class TeamMemberFromSGTeamFillerTest extends AbstractFillerTest {

	@Test
	public void testGetValue() {
		TeamMemberFromSGTeamFiller filler = new TeamMemberFromSGTeamFiller(call, file);
		String value = filler.getValue();

		assertNotNull(value);
		assertEquals("VS-TI-FL-Team36, VFS_TI_MV_RIC", value);
	}
}
