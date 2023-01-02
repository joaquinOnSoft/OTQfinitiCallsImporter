package com.opentext.qfiniti.importer.io.filler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlanetaNamingConventionTelephoneFillerTest extends PlanetaNamingConventionFillerTest {

	@Test
	public void getValue() {
		PlanetaNamingConventionTelephoneFiller filler = new PlanetaNamingConventionTelephoneFiller(call, file);
		
		assertEquals("697244857", filler.getValue());
	}
}
