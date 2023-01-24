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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;

public class WavFilterTest {

	private File[] finder(boolean recursive, File file) {
		String path = file.getParentFile().getAbsolutePath();

		assertNotNull(path);

		WavFilter filter = new WavFilter();
		File[] files = filter.finder(recursive, path);
		return files;
	}		
	
	@Test
	public void testFinder() {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("client-o/file_example_WAV_1MG.wav").getFile());

		assertNotNull(file);

		File[] files = finder(false, file);

		assertNotNull(files);
		assertTrue(files.length > 0);
		assertEquals("0001.wav", files[0].getName());
		assertEquals("0002.wav", files[1].getName());
		assertEquals("0003.wav", files[2].getName());

		assertEquals("file_example_WAV_1MG.wav", files[45].getName());
		assertEquals("file_example_WAV_2MG.wav", files[46].getName());
		assertEquals("file_example_WAV_5MG.wav", files[47].getName());
	}

	private File[] finder3Parameters(boolean recursive) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("client-p/13032476-31122021_143358-2622-5631-00307-0204697244857.wav").getFile());

		assertNotNull(file);

		File[] files = finder(recursive, file);
		return files;
	}	
	
	@Test
	public void testFinder3ParametersRecursive() {
		
		File[] files = finder3Parameters(true);

		assertNotNull(files);
		assertEquals(3, files.length);
		assertEquals("13032476-31122021_143358-2622-5631-00307-0204697244857.wav", files[0].getName());
		assertEquals("13032476-30122021_154123-2622-5631-00307-0204697244857.wav", files[1].getName());
		assertEquals("13032476-24122021_165114-2622-5631-00307-0204697244857.wav", files[2].getName());	
	}
	
	
	@Test
	public void testFinder3ParametersNotRecursive() {
		
		File[] files = finder3Parameters(false);

		assertNotNull(files);
		assertEquals(1, files.length);
		assertEquals("13032476-31122021_143358-2622-5631-00307-0204697244857.wav", files[0].getName());
	}

	
}
