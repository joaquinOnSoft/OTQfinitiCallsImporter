package com.opentext.qfiniti.importer.io.filler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlanetaNamingConventionIdFillerTest extends PlanetaNamingConventionFillerTest {

	@Test
	public void getValue() {
		PlanetaNamingConventionIdFiller filler = new PlanetaNamingConventionIdFiller(call, file);
		
		assertEquals("13032476", filler.getValue());
	}
}
