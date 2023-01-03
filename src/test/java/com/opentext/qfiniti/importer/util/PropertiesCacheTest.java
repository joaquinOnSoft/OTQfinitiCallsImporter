/*
 *   (C) Copyright 2021 OpenText and others.
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
package com.opentext.qfiniti.importer.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class PropertiesCacheTest{

	@Test
	public void testIsFile() {
		PorpertiesCache instance = PorpertiesCache.getInstance("user-mapping.properties");
		assertNotNull(instance);
		assertEquals("Natasha Romanoff", instance.getProperty("93754d84-c59e-417d-ae00-3c62a424fae3"));
		assertEquals("Bruce Banner", instance.getProperty("35424d84-c59e-417d-be63-3c62a458cad3"));
		assertEquals("Peter Parker", instance.getProperty("25854d84-c59e-417d-ae00-3c62a424bea0"));
	}
}
