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
package com.opentext.qfiniti.importer.io.metadata;

public class TikaMetadataExtractorTest extends AbstractMetadataExtractorTest {

	@Override
	protected IMetadataCreator getMetadataExtractor() {
		return new TikaMetadataExtractor();
	}	

	//		assertEquals("audio/vnd.wave", metadata.get(IMetadataCreator.CONTENT_TYPE));
}
