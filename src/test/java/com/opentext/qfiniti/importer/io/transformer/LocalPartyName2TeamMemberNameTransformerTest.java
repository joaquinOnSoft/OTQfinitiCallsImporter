package com.opentext.qfiniti.importer.io.transformer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class LocalPartyName2TeamMemberNameTransformerTest extends AbstractTransformerTest{

	private LocalPartyName2TeamMemberNameTransformer transformer;

	@Before
	public void runBeforeTestMethod() {
		super.before();
		transformer = new LocalPartyName2TeamMemberNameTransformer(path);
	}

	@Test
	public void transformOneFamilyName() {
		String teamMemberName = transformer.transform("IVAN GARCIA");
		assertNotNull(teamMemberName);
		assertEquals("GARCIA, IVAN", teamMemberName);
	}

	@Test
	public void transformTwoFamilyNames() {
		String teamMemberName = transformer.transform("SANTIAGO PEREZ PEREZ");
		assertNotNull(teamMemberName);
		assertEquals("PEREZ PEREZ, SANTIAGO", teamMemberName);
	}

	@Test
	public void transformTwoFirstNames() {
		String teamMemberName = transformer.transform("MARIA FERNANDA GOMEZ GOMEZ");
		assertNotNull(teamMemberName);
		assertEquals("GOMEZ GOMEZ, MARIA FERNANDA", teamMemberName);
	}

	@Test
	public void transformNullName() {
		String teamMemberName = transformer.transform(null);
		assertNotNull(teamMemberName);
		assertEquals("Doe, John", teamMemberName);
	}

	@Test
	public void transformEmptyString() {
		String teamMemberName = transformer.transform("");
		assertNotNull(teamMemberName);
		assertEquals("Doe, John", teamMemberName);
	}
}
