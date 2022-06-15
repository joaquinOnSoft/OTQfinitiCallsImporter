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
 *     Joaquín Garzón - initial implementation
 *
 */
package com.opentext.qfiniti.importer.io.transformer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class RemoveHyphensTransformerTest extends AbstractTransformerTest{

	private RemoveHyphensTransformer transformer;
	
	@Before
	public void runBeforeTestMethod() {
		super.before();
		transformer = new RemoveHyphensTransformer(null);
	}	

	@Test
	public void transform() {
		String value = transformer.transform("25854d84-c59e-417d-ae00-3c62a424bea0");
		assertNotNull(value);
		assertEquals("25854d84c59e417dae003c62a424bea0", value);		
		
	}
	
	@Test
	public void transformNullValue() {
		String value = transformer.transform(null);
		assertNotNull(value);
		assertEquals("", value);			
	}	
}
