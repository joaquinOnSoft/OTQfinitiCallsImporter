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
package com.opentext.qfiniti.importer.io;

import java.io.File;

import org.junit.Before;

public class AbstractMetadataExtractorTest {

	protected File file;

	public AbstractMetadataExtractorTest() {
		super();
	}

	@Before
	public void runBeforeTestMethod() {
		ClassLoader classLoader = getClass().getClassLoader();
		file = new File(classLoader.getResource("file_example_WAV_1MG.wav").getFile());
	}

}