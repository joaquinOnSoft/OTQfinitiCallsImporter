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
