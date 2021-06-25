package com.opentext.qfiniti.importer.io.filler;

import java.io.File;

import com.opentext.qfiniti.importer.pojo.CallRecording;

public class TeamMemberFiller extends AbstractFiller {

	private static final String[] names = { 
			"Chang, Monica", "Romanoff, Natasha", 
			"Richards, Reed", "Wagner, Kurt",
			"Parker, Peter", "Howlett, James", 
			"Natchios, Elektra", "LeBeau, Remy" 
			};

	public TeamMemberFiller(CallRecording call, String path, String fileName) {
		super(call, path, fileName);
	}

	public TeamMemberFiller(CallRecording call, String filePath) {
		super(call, filePath);
	}

	public TeamMemberFiller(CallRecording call, File file) {
		super(call, file);
	}

	@Override
	public String getValue() {
		int index = getRandom(0, names.length - 1);
		return names[index];
	}
}
