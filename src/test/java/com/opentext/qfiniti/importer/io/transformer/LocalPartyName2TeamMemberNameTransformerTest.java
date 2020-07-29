package com.opentext.qfiniti.importer.io.transformer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class LocalPartyName2TeamMemberNameTransformerTest {
	@Test
	public void transform() {
		LocalPartyName2TeamMemberNameTransformer transformer = new LocalPartyName2TeamMemberNameTransformer();
		String teamMemberName = transformer.transform("IVAN GARCIA");
		assertNotNull(teamMemberName);
		assertEquals("GARCIA, IVAN", teamMemberName);

		teamMemberName = transformer.transform("SANTIAGO PEREZ PEREZ");
		assertNotNull(teamMemberName);
		assertEquals("PEREZ PEREZ, SANTIAGO", teamMemberName);
		
		teamMemberName = transformer.transform("MARIA FERNANDA GOMEZ GOMEZ");
		assertNotNull(teamMemberName);
		assertEquals("GOMEZ GOMEZ, MARIA FERNANDA", teamMemberName);		
	}
}
