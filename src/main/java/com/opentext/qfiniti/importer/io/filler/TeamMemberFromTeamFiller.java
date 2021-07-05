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
 *     Joaquín Garzón - initial implementation
 *
 */
package com.opentext.qfiniti.importer.io.filler;

import java.io.File;

import com.opentext.qfiniti.importer.pojo.CallRecording;

public class TeamMemberFromTeamFiller extends AbstractFiller {

	private static final String FIELD_TEAM = "Team";
	
	private static final String TEAM_PREFIX = "VS-TI-FL-Team";

	public TeamMemberFromTeamFiller(CallRecording call, String path, String fileName) {
		super(call, path, fileName);
	}

	public TeamMemberFromTeamFiller(CallRecording call, String filePath) {
		super(call, filePath);
	}

	public TeamMemberFromTeamFiller(CallRecording call, File file) {
		super(call, file);
	}

	/**
	 * Generate a member team name from the properties "SG" and "Team"
	 */
	@Override
	public String getValue() {
		String teamMember = null;
		
		String team = call.getExtendedField(FIELD_TEAM);
		
		// Recover the team name form the "Group hierarchy",
		// just in case the "Team" field has been mapped
		if(team == null) {
			team = call.getGroupHierachy();
		}
		
		if (team != null) {
			String teamId = team.replace(TEAM_PREFIX, "");
			
			teamMember = (new StringBuilder())
					.append(team)
					.append(", Agent")
					.append(teamId)
					.toString();		
		}
		
		return teamMember;
	}
}

