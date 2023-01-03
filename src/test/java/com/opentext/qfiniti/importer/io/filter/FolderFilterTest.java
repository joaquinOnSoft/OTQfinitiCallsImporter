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
package com.opentext.qfiniti.importer.io.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;

import org.junit.jupiter.api.Test;

public class FolderFilterTest {
	public static String SAMPLES_FOLDER = "client-i/samples";

	@Test
	public void testFinder() {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(SAMPLES_FOLDER).getFile());

		assertNotNull(file);

		String path = file.getAbsolutePath();
		System.out.println(path);

		assertNotNull(path);

		FolderFilter filter = new FolderFilter();
		File[] files = filter.finder(path);

		assertNotNull(files);
		assertEquals(2, files.length);
		assertEquals("20191021", files[0].getName());
		assertEquals("20191022", files[1].getName());
	}

}
