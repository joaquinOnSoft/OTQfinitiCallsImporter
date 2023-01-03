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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

public class ExtensionPrefix2FileNameTransformerTest extends AbstractTransformerTest{

	private static final String FILE_PREFIX[] = {
			"ext2960006643_05_04_2021_13;52;26",
			"ext2960006641_05_04_2021_10;17;"
		};
	private static final String FILE_NAME[] = {
			"ext2960006643_05_04_2021_13;52;26_21559_it1483yw.wav",
			"ext2960006641_05_04_2021_10;17;53_12287_it1483yw.wav"
		};
	
	@Test
	public void transform() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("client-v").getFile());
		
		ExtensionPrefix2FileNameTransformer transformer = new ExtensionPrefix2FileNameTransformer(file.getAbsolutePath());
		
		String fileName = null;
		int size = FILE_PREFIX.length;
		
		for(int i=0; i<size; i++) {
			fileName = transformer.transform(FILE_PREFIX[i]);
			assertEquals(FILE_NAME[i], fileName);
		}
	}

}
