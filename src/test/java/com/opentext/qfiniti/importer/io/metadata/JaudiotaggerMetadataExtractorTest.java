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

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class JaudiotaggerMetadataExtractorTest extends AbstractMetadataExtractorTest {

	@Test
	public void testExtract() {
		assertNotNull(file);

		Map<String, String> metadata = null;
		IMetadataCreator extractor = new JaudiotaggerMetadataExtractor();

		try {
			metadata = extractor.extract(file);
		} catch (IOException e) {
			fail(e.getMessage());
		}

		assertNotNull(metadata);
		assertEquals("", metadata.get(IMetadataCreator.TITLE));
		assertEquals("", metadata.get(IMetadataCreator.ARTIST));
		assertEquals("33", metadata.get(IMetadataCreator.DURATION));
		assertEquals("8000", metadata.get(IMetadataCreator.SAMPLE_RATE));
		assertEquals("256", metadata.get(IMetadataCreator.BITS));
		assertEquals("2", metadata.get(IMetadataCreator.CHANNELS));
	}
}
