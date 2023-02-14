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
package com.opentext.qfiniti.importer.io.filler;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opentext.qfiniti.importer.io.metadata.IMetadataCreator;
import com.opentext.qfiniti.importer.io.metadata.JaffreeMetadataExtractor;
import com.opentext.qfiniti.importer.io.metadata.JaudiotaggerMetadataExtractor;
import com.opentext.qfiniti.importer.io.metadata.JavaMetadataExtractor;
import com.opentext.qfiniti.importer.io.metadata.TikaMetadataExtractor;
import com.opentext.qfiniti.importer.pojo.CallRecording;

public class DurationFromMetadataFiller extends AbstractFiller {
	private static final Logger log = LogManager.getLogger(DurationFromMetadataFiller.class);

	public DurationFromMetadataFiller(CallRecording call, String path, String fileName) {
		super(call, path, fileName);
	}

	public DurationFromMetadataFiller(CallRecording call, String filePath) {
		super(call, filePath);
	}

	public DurationFromMetadataFiller(CallRecording call, File file) {
		super(call, file);
	}

	/**
	 * Get the audio duration from the metadata embedded in the audio  
	 */
	@Override
	public String getValue() {
		String duration = getDuration(new JaudiotaggerMetadataExtractor());
		if (duration == null) {
			duration = getDuration(new TikaMetadataExtractor());
		} 
		if (duration == null) {
			duration = getDuration(new JavaMetadataExtractor());
		}
		if (duration == null || duration.compareTo("0") == 0) {
			duration = getDuration(new JaffreeMetadataExtractor());
		}		
		
		return duration;
	}

	private String getDuration(IMetadataCreator extractor) {
		Map<String, String> metadata = null;
		String duration = null;

		try {
			metadata = extractor.extract(file);
		} catch (IOException e) {
			log.warn(e.getMessage());
		}

		if (metadata != null) {
			duration = metadata.get(IMetadataCreator.DURATION);
		}

		return duration;
	}

}
