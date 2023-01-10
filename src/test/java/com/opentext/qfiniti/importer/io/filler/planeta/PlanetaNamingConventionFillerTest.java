/*
 *   (C) Copyright 2023 OpenText and others.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *   Contributors:
 *     Joaquín Garzón - initial implementation
 *
 */
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
