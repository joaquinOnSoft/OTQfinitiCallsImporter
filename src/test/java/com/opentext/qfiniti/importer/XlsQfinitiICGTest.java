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
package com.opentext.qfiniti.importer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.List;


import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.pojo.MappingConfig;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;


public class XlsQfinitiICGTest {

	@Test
	public void testGenerate() {
		List<CallRecording> recordings = null;
		
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("client-i/samples-flatered").getFile());
		String path = file.getAbsolutePath();
		
		File jsonFile = new File(classLoader.getResource("client-i/client-i-mapping.json").getFile());
		JSonConfigReader jsonConfigReader = new JSonConfigReader();
		MappingConfig mapping = jsonConfigReader.read(jsonFile);
				
		XlsQfinitiICG configGenerator = new XlsQfinitiICG(path);
		configGenerator.setOutput("calls.xls");
		configGenerator.setMappingConfig(mapping);
		try {
			recordings = configGenerator.generate();
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (InvalidFormatException e) {
			fail(e.getMessage());
		}
		
		assertNotNull(recordings);
	}

}
