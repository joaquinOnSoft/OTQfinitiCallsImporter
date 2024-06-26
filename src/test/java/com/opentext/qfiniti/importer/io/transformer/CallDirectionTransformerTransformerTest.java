/*
 *   (C) Copyright 2019 OpenText and others.
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
 *     Joaqu�n Garz�n - initial implementation
 *
 */
package com.opentext.qfiniti.importer.io.transformer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CallDirectionTransformerTransformerTest extends AbstractTransformerTest{

	private CallDirectionTransformer transformer;
	
	@BeforeEach
	public void runBeforeTestMethod() {
		super.setup();
		transformer = new CallDirectionTransformer(null);
	}	

	@Test
	public void transform() {
		String callDirection = transformer.transform("Hello");
		assertNotNull(callDirection);
		assertEquals("0", callDirection);		
		
		callDirection = transformer.transform("Inbound");
		assertNotNull(callDirection);
		assertEquals("1", callDirection);
		
		callDirection = transformer.transform("Outbound");
		assertNotNull(callDirection);
		assertEquals("2", callDirection);		
	}
	
	@Test
	public void transformNullCallDirection() {
		String callDirection = transformer.transform(null);
		assertNotNull(callDirection);
		assertEquals("0", callDirection);			
	}	
}
