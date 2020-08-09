package com.opentext.qfiniti.importer.io.filler;

import java.io.File;

public class TeamMemberFiller extends AbstractFiller {
	
	private static final String [] names = {
			"Chang, Monica",
			"Romanoff, Natasha",
			"Richards, Reed",
			"Wagner, Kurt",
			"Parker, Peter",
			"Howlett, James",
			"Natchios, Elektra",
			"LeBeau, Remy"
			};

	public TeamMemberFiller(String path, String fileName) {
		super(path, fileName);
	}
	
	public TeamMemberFiller(String filePath) {
		super(filePath);
	}	

	public TeamMemberFiller(File file) {
		super(file); 
	}			
	
	@Override
	public String getValue() {
		int index =getRandom(0, names.length - 1);
		return names[index];
	}

}
