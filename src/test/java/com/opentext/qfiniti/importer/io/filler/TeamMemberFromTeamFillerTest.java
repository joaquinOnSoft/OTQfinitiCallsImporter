/*
 *   (C) Copyright 2019 OpenText and others.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *   Contributors:
 *     Joaqu�n Garz�n - initial implementation
 *
 */
package com.opentext.qfiniti.importer.io.filler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class TeamMemberFromTeamFillerTest extends AbstractFillerTest {

	@Test
	public void testGetValue() {
		TeamMemberFromTeamFiller filler = new TeamMemberFromTeamFiller(call, file);
		String value = filler.getValue();

		assertNotNull(value);
		assertEquals("VS-TI-FL-Team36, agent36", value);
	}
	
	@Test
	public void testGetValueWithEmptyTeam() {
		call.addExtendedField("Team", null);
		call.setGroupHierachy(null);

		TeamMemberFromTeamFiller filler = new TeamMemberFromTeamFiller(call, file);
		String value = filler.getValue();

		assertNotNull(value);
		assertEquals("UNKNOWN, agent", value);
	}	
}
