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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

public class UserId2UserSurnameTransformerTest extends AbstractTransformerTest{

	private UserId2UserSurnameTransformer transformer;
	
	@Before
	public void runBeforeTestMethod() {
		super.before();
		transformer = new UserId2UserSurnameTransformer(null);
	}	

	@Test
	public void transform() {
		//25854d84-c59e-417d-ae00-3c62a424bea0=Peter Parker		
		String value = transformer.transform("25854d84-c59e-417d-ae00-3c62a424bea0");
		assertNotNull(value);
		assertEquals("Parker", value);				
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
		assertEquals("Fantastico", value);			

		//99454d43-b82a-893e-fe88-5f64b424aeb1=Bruce Banner Jr.
		value = transformer.transform("99454d43-b82a-893e-fe88-5f64b424aeb1");
		assertNotNull(value);
		assertEquals("99454d43-b82a-893e-fe88-5f64b424aeb1", value);		
	}	
}
