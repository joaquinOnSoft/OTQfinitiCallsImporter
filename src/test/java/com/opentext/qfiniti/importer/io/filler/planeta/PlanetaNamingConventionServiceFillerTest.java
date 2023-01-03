package com.opentext.qfiniti.importer.io.filler.planeta;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlanetaNamingConventionServiceFillerTest extends PlanetaNamingConventionFillerTest {

	@Test
	public void getValue() {
		PlanetaNamingConventionServiceFiller filler = new PlanetaNamingConventionServiceFiller(call, file);
		
		assertEquals("2622", filler.getValue());
	}
}
