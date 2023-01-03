package com.opentext.qfiniti.importer.io.filler.planeta;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.opentext.qfiniti.importer.io.filler.AbstractFillerTest;

public class PlanetaNamingConventionFillerTest extends AbstractFillerTest {
	@Override
	protected String getFilePath() {
		return "client-p/13032476-31122021_143358-2622-5631-00307-0204697244857.gsm";
	}	
	
	@Test
	public void testGetField() {
		PlanetaNamingConventionFiller filler = new PlanetaNamingConventionIdFiller(call, file);
	
		assertEquals("13032476", filler.getField(PlanetaNamingConventionFiller.FIELD_ID));
		assertEquals("31/12/2021 14:33:58", filler.getField(PlanetaNamingConventionFiller.FIELD_DATE));
		assertEquals("2622", filler.getField(PlanetaNamingConventionFiller.FIELD_SERVICE));
		assertEquals("5631", filler.getField(PlanetaNamingConventionFiller.FIELD_AGENT));
		assertEquals("00307", filler.getField(PlanetaNamingConventionFiller.FIELD_DURATION));
		assertEquals("0204", filler.getField(PlanetaNamingConventionFiller.FIELD_ROUTE));		
		assertEquals("697244857", filler.getField(PlanetaNamingConventionFiller.FIELD_TELEPHONE));		
	}
}
