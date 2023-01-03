package com.opentext.qfiniti.importer.io.filler.planeta;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlanetaNamingConventionRouteFillerTest extends PlanetaNamingConventionFillerTest {

	@Test
	public void getValue() {
		PlanetaNamingConventionRouteFiller filler = new PlanetaNamingConventionRouteFiller(call, file);
		
		assertEquals("0204", filler.getValue());
	}
}
