/*
 *   (C) Copyright 2022 OpenText and others.
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserId2UserNameTransformerTest extends AbstractTransformerTest{

	private UserId2UserNameTransformer transformer;
	
	@BeforeEach
	public void runBeforeTestMethod() {
		super.setup();
		transformer = new UserId2UserNameTransformer(null);
	}	

	@Test
	public void transform() {
		//25854d84-c59e-417d-ae00-3c62a424bea0=Peter Parker
		String value = transformer.transform("25854d84-c59e-417d-ae00-3c62a424bea0");
		assertNotNull(value);
		assertEquals("Peter", value);				
	}
	
	@Test
	public void transformNullValue() {
		String value = transformer.transform(null);
		assertNotNull(value);
		assertEquals("", value);			
	}	
	
	@Test
	public void transformNameWithPoint() {
		//37474a46-e22c-276c-cd39-4f64b424bfc2=Mr. Fantastico
		String value = transformer.transform("37474a46-e22c-276c-cd39-4f64b424bfc2");
		assertNotNull(value);
		assertEquals("Mr", value);			

		//99454d43-b82a-893e-fe88-5f64b424aeb1=Bruce Banner Jr.
		value = transformer.transform("99454d43-b82a-893e-fe88-5f64b424aeb1");
		assertNotNull(value);
		assertEquals("Bruce Banner Jr", value);		
	}		
}
