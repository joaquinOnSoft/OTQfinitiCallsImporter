package com.opentext.qfiniti.importer.io.filler.planeta;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlanetaNamingConventionIdFillerTest extends PlanetaNamingConventionFillerTest {

	@Test
	public void getValue() {
		PlanetaNamingConventionIdFiller filler = new PlanetaNamingConventionIdFiller(call, file);
		
		assertEquals("13032476", filler.getValue());
	}
}
