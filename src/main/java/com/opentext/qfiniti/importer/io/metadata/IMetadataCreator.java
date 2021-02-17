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

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface IMetadataCreator {

	final String PARSED_BY = "X-Parsed-By";
	final String SAMPLE_RATE = "xmpDM:audioSampleRate";
	final String CHANNELS = "channels";
	final String BITS = "bits";
	final String RESOURCE_NAME = "resourceName";
	final String CONTENT_LENGTH = "Content-Length";
	final String ENCODING = "encoding";
	final String SAMPLE_TYPE = "xmpDM:audioSampleType";
	final String CONTENT_TYPE = "Content-Type";

	final String DURATION = "duration";
	final String ARTIST = "artist";
	final String TITLE = "title";

	/**
	 * 
	 * @param audio Audio file
	 * @return Map with a list of available metadata
	 * @throws IOException If there is some problem accessing the file
	 */
	Map<String, String> extract(File audio) throws IOException;

}