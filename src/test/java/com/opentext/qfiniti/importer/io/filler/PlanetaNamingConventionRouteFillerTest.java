package com.opentext.qfiniti.importer.io.filler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlanetaNamingConventionRouteFillerTest extends PlanetaNamingConventionFillerTest {

	@Test
	public void getValue() {
		PlanetaNamingConventionRouteFiller filler = new PlanetaNamingConventionRouteFiller(call, file);
		
		assertEquals("0204", filler.getValue());
	}
}
