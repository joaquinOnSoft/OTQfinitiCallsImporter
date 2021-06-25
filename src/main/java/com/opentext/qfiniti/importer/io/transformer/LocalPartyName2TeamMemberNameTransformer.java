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
package com.opentext.qfiniti.importer.io.transformer;

public class LocalPartyName2TeamMemberNameTransformer extends AbstractTransformer {

	private static final String DEFAULT_NAME = "Doe, John";
	
	public LocalPartyName2TeamMemberNameTransformer(String path) {
		super(path);
	}
	
	/**
	 * Transforms a team member name from one of these formats:
	 * 
	 * - 1st_NAME 1st_FAMILY_NAME 2nd_FAMILY_NAME - 1st_NAME 2nd_NAME
	 * 1st_FAMILY_NAME 2nd_FAMILY_NAME - 1st_NAME 1st_FAMILY_NAME
	 * 
	 * to one of these: - 1st_FAMILY_NAME 2nd_FAMILY_NAME, 1st_NAME -
	 * 1st_FAMILY_NAME 2nd_FAMILY_NAME, 1st_NAME 2nd_NAME - 1st_FAMILY_NAME,
	 * 1st_NAME
	 * 
	 * @param teamMemberName - Team member name
	 * @return Formated team member name
	 */
	@Override
	public String transform(String teamMemberName) {
		String teamMemberNameOutput = null;
		if (teamMemberName == null || teamMemberName.compareTo("") == 0) {
			teamMemberNameOutput = DEFAULT_NAME;
		} else if (!teamMemberName.contains(",")) {
			String[] sections = teamMemberName.split(" ");

			if (sections != null) {
				// NAME + FAMILY NAME 1 + FAMILY NAME 2 = 3 sections
				int nSections = sections.length;
				int nLastSection = nSections - 1;
				int nInit = 1;

				if (nSections > 3) {
					nInit = 2;
				}

				StringBuilder name = new StringBuilder();
				for (int i = nInit; i < nSections; i++) {
					name.append(sections[i]);
					if (i == nLastSection) {
						name.append(",");
					}

					name.append(" ");
				}

				for (int i = 0; i < nInit; i++) {
					name.append(sections[i]);
					if (i != (nInit - 1)) {
						name.append(" ");
					}
				}

				teamMemberNameOutput = name.toString();
			}

		} else {
			teamMemberNameOutput = teamMemberName;
		}

		return teamMemberNameOutput;
	}

}
