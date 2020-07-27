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
package com.opentext.qfiniti.importer.io;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class XlsFilterTest {
	public static String EXCEL_SAMPLE = "iberdrola/20191021.xls";

	@Test
	public void testFinder() {
			
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(EXCEL_SAMPLE).getFile());
		
		assertNotNull(file);
		
		String path = file.getParentFile().getAbsolutePath();
		
		assertNotNull(path);
		
		XlsFilter filter = new XlsFilter();
		File[] files = filter.finder(path);
		
		assertNotNull(files);
		assertTrue(files.length > 0);
		assertEquals("20191021.xls", files[0].getName());
	}

}
