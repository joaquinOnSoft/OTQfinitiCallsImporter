package com.opentext.qfiniti.importer.io.filler.planeta;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PlanetaNamingConventionTelephoneFillerTest extends PlanetaNamingConventionFillerTest {

	@Test
	public void getValue() {
		PlanetaNamingConventionTelephoneFiller filler = new PlanetaNamingConventionTelephoneFiller(call, file);
		
		assertEquals("697244857", filler.getValue());
	}
}
