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

	@Test
	public void testFinder() {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("client-o/file_example_WAV_1MG.wav").getFile());

		assertNotNull(file);

		String path = file.getParentFile().getAbsolutePath();

		assertNotNull(path);

		WavFilter filter = new WavFilter();
		File[] files = filter.finder(path);

		assertNotNull(files);
		assertTrue(files.length > 0);
		assertEquals("0001.wav", files[0].getName());
		assertEquals("0002.wav", files[1].getName());
		assertEquals("0003.wav", files[2].getName());

		assertEquals("file_example_WAV_1MG.wav", files[45].getName());
		assertEquals("file_example_WAV_2MG.wav", files[46].getName());
		assertEquals("file_example_WAV_5MG.wav", files[47].getName());
	}

}
