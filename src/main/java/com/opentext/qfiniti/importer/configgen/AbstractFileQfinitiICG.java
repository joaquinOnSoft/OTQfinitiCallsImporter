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
package com.opentext.qfiniti.importer.configgen;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.opentext.qfiniti.importer.io.IReader;
import com.opentext.qfiniti.importer.io.filter.FileFilter;
import com.opentext.qfiniti.importer.pojo.CallRecording;

/**
 * OpenText(TM) Qfiniti Importer Configuration Generator
 * 
 * @author Joaquín Garzón
 */
public abstract class AbstractFileQfinitiICG extends AbstractQfinitiICG {

	public AbstractFileQfinitiICG(String path, String extension) {
		super(path, extension);
	}

	protected abstract IReader getReader();

	/**
	 * Read Excel files
	 * 
	 * @param path
	 * @param recordings
	 * @return
	 */
	protected Map<String, CallRecording> readDataFiles(String path, Map<String, CallRecording> recordings) {
		if (extension != null) {
			FileFilter fFilter = new FileFilter();

			File metadataFiles[] = fFilter.finder(path, extension);
			if (metadataFiles != null && metadataFiles.length > 0) {
				IReader reader = getReader();
				for (File file : metadataFiles) {
					List<CallRecording> tmpRecordings = reader.read(file.getAbsolutePath(), mappingConfig);
					recordings = dumpListToMap(recordings, tmpRecordings);
				}
			}
		}

		return recordings;
	}

	private Map<String, CallRecording> dumpListToMap(Map<String, CallRecording> map, List<CallRecording> list) {
		for (CallRecording callRecording : list) {
			map.put(callRecording.getFileName(), callRecording);
		}

		return map;
	}

}
