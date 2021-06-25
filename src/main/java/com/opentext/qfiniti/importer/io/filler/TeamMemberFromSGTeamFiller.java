package com.opentext.qfiniti.importer.io.filler;

import java.io.File;

import com.opentext.qfiniti.importer.pojo.CallRecording;

public class TeamMemberFromSGTeamFiller extends AbstractFiller {

	private static final String FIELD_SG = "SG";
	private static final String FIELD_TEAM = "Team";

	public TeamMemberFromSGTeamFiller(CallRecording call, String path, String fileName) {
		super(call, path, fileName);
	}

	public TeamMemberFromSGTeamFiller(CallRecording call, String filePath) {
		super(call, filePath);
	}

	public TeamMemberFromSGTeamFiller(CallRecording call, File file) {
		super(call, file);
	}

	@Override
	public String getValue() {
		String teamMember = null;
		
		String sg = call.getExtendedField(FIELD_SG);
		String team = call.getExtendedField(FIELD_TEAM);
		if(team == null) {
			team = call.getGroupHierachy();
		}
		
		if (sg != null && team != null) {
			teamMember = (new StringBuilder())
					.append(team)
					.append(", ")
					.append(sg)
					.toString();		
		}
		
		return teamMember;
	}

}

