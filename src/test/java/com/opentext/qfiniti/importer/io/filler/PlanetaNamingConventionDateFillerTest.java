package com.opentext.qfiniti.importer.io.filler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlanetaNamingConventionDateFillerTest extends PlanetaNamingConventionFillerTest {

	@Test
	public void getValue() {
		PlanetaNamingConventionDateFiller filler = new PlanetaNamingConventionDateFiller(call, file);
		
		assertEquals("31/12/2021 14:33:58", filler.getValue());
	}
}
