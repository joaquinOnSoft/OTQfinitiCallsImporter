package com.opentext.qfiniti.importer.io.filler;

import java.io.File;

public class GroupHierarchyDolceVitaFiller extends AbstractFiller {

	private static final String groups[] = {
			"VS-TI-FL-Team2", "VS-TI-FL-Team4", "VS-TI-FL-Team5", "VS-TI-FL-Team6",
			"VS-TI-FL-Team9", "VS-TI-FL-Team10", "VS-TI-FL-Team12", "VS-TI-FL-Team14", 
			"VS-TI-FL-Team17", "VS-TI-FL-Team20", "VS-TI-FL-Team21", "VS-TI-FL-Team28", 
			"VS-TI-FL-Team29", "VS-TI-FL-Team31", "VS-TI-FL-Team36", "VS-TI-FL-Team39", 
			"VS-TI-FL-Team40", "VS-TI-FL-Team49", "VS-TI-FL-Team50", "VS-TI-FL-Team51", 
			"VS-TI-FL-Team55", "VS-TI-FL-Team59"
			};
	
	public GroupHierarchyDolceVitaFiller(String path, String fileName) {
		super(path, fileName);
	}

	public GroupHierarchyDolceVitaFiller(String filePath) {
		super(filePath);
	}

	public GroupHierarchyDolceVitaFiller(File file) {
		super(file);
	}

	@Override
	public String getValue() {
		int index = getRandom(0, groups.length - 1);
		return groups[index];		
	}
}
