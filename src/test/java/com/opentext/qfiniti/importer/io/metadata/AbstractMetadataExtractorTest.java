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
package com.opentext.qfiniti.importer.io.metadata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class AbstractMetadataExtractorTest {

	protected File file;

	public AbstractMetadataExtractorTest() {
		super();
	}

	@BeforeEach
	public void runBeforeTestMethod() {
		ClassLoader classLoader = getClass().getClassLoader();
		file = new File(classLoader.getResource("client-o/file_example_WAV_1MG.wav").getFile());
	}
	
	protected abstract IMetadataCreator getMetadataExtractor();
	
	@Test
	public void testExtract() {
		assertNotNull(file);

		Map<String, String> metadata = null;
		IMetadataCreator extractor = getMetadataExtractor();

		try {
			metadata = extractor.extract(file);
		} catch (IOException e) {
			fail(e.getMessage());
		}

		assertNotNull(metadata);
		
		if(metadata.containsKey(IMetadataCreator.TITLE))
			assertEquals("Impact Moderato", metadata.get(IMetadataCreator.TITLE));
		
		if(metadata.containsKey(IMetadataCreator.ARTIST))
			assertEquals("Kevin MacLeod", metadata.get(IMetadataCreator.ARTIST));
		
		//assertEquals("pcm_s16le", metadata.get(IMetadataCreator.ENCODING));
		
		if(metadata.containsKey(IMetadataCreator.DURATION)) {
			assertEquals("33", metadata.get(IMetadataCreator.DURATION));
		}
		
		if(metadata.containsKey(IMetadataCreator.CHANNELS)) {
			assertEquals("2", metadata.get(IMetadataCreator.CHANNELS));
		}
		
		if(metadata.containsKey(IMetadataCreator.SAMPLE_RATE)) {
			assertEquals("8000", metadata.get(IMetadataCreator.SAMPLE_RATE));
		}
		
		if(metadata.containsKey(IMetadataCreator.BITS)) {
			assertEquals("16", metadata.get(IMetadataCreator.BITS));
		}
	}	

}