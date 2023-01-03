package com.opentext.qfiniti.importer.io.filler.planeta;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlanetaNamingConventionDurationFillerTest extends PlanetaNamingConventionFillerTest {

	@Test
	public void getValue() {
		PlanetaNamingConventionDurationFiller filler = new PlanetaNamingConventionDurationFiller(call, file);
		
		assertEquals("00307", filler.getValue());
	}
}
