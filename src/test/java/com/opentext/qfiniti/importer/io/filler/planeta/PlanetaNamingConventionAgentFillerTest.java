package com.opentext.qfiniti.importer.io.filler.planeta;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlanetaNamingConventionAgentFillerTest extends PlanetaNamingConventionFillerTest {

	@Test
	public void getValue() {
		PlanetaNamingConventionAgentFiller filler = new PlanetaNamingConventionAgentFiller(call, file);
		
		assertEquals("5631", filler.getValue());
	}
}
