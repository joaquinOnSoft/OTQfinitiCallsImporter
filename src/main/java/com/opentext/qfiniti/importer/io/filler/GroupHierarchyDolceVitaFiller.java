package com.opentext.qfiniti.importer.io.filler;

import java.io.File;

import com.opentext.qfiniti.importer.pojo.CallRecording;

public class GroupHierarchyDolceVitaFiller extends AbstractFiller {

	private static final String groups[] = {
			"VS-TI-FL-Team2", "VS-TI-FL-Team4", "VS-TI-FL-Team5", "VS-TI-FL-Team6",
			"VS-TI-FL-Team9", "VS-TI-FL-Team10", "VS-TI-FL-Team12", "VS-TI-FL-Team14", 
			"VS-TI-FL-Team17", "VS-TI-FL-Team20", "VS-TI-FL-Team21", "VS-TI-FL-Team28", 
			"VS-TI-FL-Team29", "VS-TI-FL-Team31", "VS-TI-FL-Team36", "VS-TI-FL-Team39", 
			"VS-TI-FL-Team40", "VS-TI-FL-Team49", "VS-TI-FL-Team50", "VS-TI-FL-Team51", 
			"VS-TI-FL-Team55", "VS-TI-FL-Team59"
			};
	
	public GroupHierarchyDolceVitaFiller(CallRecording call, String path, String fileName) {
		super(call, path, fileName);
	}

	public GroupHierarchyDolceVitaFiller(CallRecording call, String filePath) {
		super(call, filePath);
	}

	public GroupHierarchyDolceVitaFiller(CallRecording call, File file) {
		super(call, file);
	}

	/**
	 * Generate a random team name for the "Dolce Vita" client.
	 * The generated team name follows this pattern:
	 * <br/> 
	 *    VS-TI-FL-Team[XX]
	 * <br/>   
	 * Where: 
	 * <ul>
	 *    <li><strong>VS-TI-FL-Team</strong> is a hardcoded prefix</li>
	 *    <li><strong>[XX]</strong> is a number from 01 to 99.</li>
	 * </ul>   
	 */
	@Override
	public String getValue() {
		int index = getRandom(0, groups.length - 1);
		return groups[index];		
	}
}
