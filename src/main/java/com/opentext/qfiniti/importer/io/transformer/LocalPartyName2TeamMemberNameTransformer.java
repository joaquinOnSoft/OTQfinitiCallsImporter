package com.opentext.qfiniti.importer.io.transformer;

public class LocalPartyName2TeamMemberNameTransformer implements ITransformer{

	private static final String DEFAULT_NAME = "Doe, John";
	/**
	 * Transforms a team member name from one of these formats:
	 * 
	 *    - 1st_NAME 1st_FAMILY_NAME 2nd_FAMILY_NAME
	 *    - 1st_NAME 2nd_NAME 1st_FAMILY_NAME 2nd_FAMILY_NAME
	 *    - 1st_NAME 1st_FAMILY_NAME
	 *    
	 * to one of these:
	 *    - 1st_FAMILY_NAME 2nd_FAMILY_NAME, 1st_NAME 
	 *    - 1st_FAMILY_NAME 2nd_FAMILY_NAME, 1st_NAME 2nd_NAME
	 *    - 1st_FAMILY_NAME, 1st_NAME 
	 * @param teamMemberName - Team member name
	 * @return Formated team member name 
	 */
	@Override
	public String transform(String teamMemberName) {
		String teamMemberNameOutput = null;
		if(teamMemberName == null || teamMemberName.compareTo("") == 0) {
			teamMemberNameOutput = DEFAULT_NAME;
		}
		else if(!teamMemberName.contains(",")) {
			String[] sections = teamMemberName.split(" ");

			if(sections != null) {
				//NAME + FAMILY NAME 1 + FAMILY NAME 2 = 3 sections
				int nSections = sections.length;
				int nLastSection = nSections - 1;
				int nInit = 1;


				if(nSections > 3) {
					nInit = 2;
				}
				
				StringBuilder name = new StringBuilder(); 
				for(int i=nInit; i<nSections; i++) {
					name.append(sections[i]);
					if(i == nLastSection) {
						name.append(",");
					}
					
					name.append(" ");
				}

				for(int i=0; i<nInit; i++) {
					name.append(sections[i]);
					if(i != (nInit -1)) {
						name.append(" ");
					}
				}
				
				
				teamMemberNameOutput = name.toString();
			}

		}
		else {
			teamMemberNameOutput = teamMemberName;	
		}
		
		return teamMemberNameOutput;
	}

}
