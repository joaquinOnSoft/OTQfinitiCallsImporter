package com.opentext.qfiniti.importer.io.filler.planeta;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlanetaNamingConventionDateFillerTest extends PlanetaNamingConventionFillerTest {

	@Test
	public void getValue() {
		PlanetaNamingConventionDateFiller filler = new PlanetaNamingConventionDateFiller(call, file);
		
		assertEquals("31/12/2021 14:33:58", filler.getValue());
	}
}
