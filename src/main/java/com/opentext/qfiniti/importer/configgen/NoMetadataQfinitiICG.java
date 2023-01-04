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
import java.util.Map;

import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.pojo.FieldFiller;
import com.opentext.qfiniti.importer.util.ImportUtils;

/**
 * OpenText(TM) Qfiniti Importer Configuration Generator
 * 
 * @author Joaquín Garzón
 */
public class NoMetadataQfinitiICG extends AbstractQfinitiICG {

	public NoMetadataQfinitiICG(String path) {
		super(path);
	}

	@Override
	protected Map<String, CallRecording> readDataFiles(String path, Map<String, CallRecording> recordings) {
		// Intentionally doing nothing
		return recordings;
	}

	@Override
	protected Map<String, CallRecording> readAudioFiles(String path, Map<String, CallRecording> recordings) {
		File audioFiles[] = getAudioFiles(path);
		
		if (audioFiles != null && audioFiles.length > 0) {

			CallRecording call = null;
			for (File file : audioFiles) {
				log.info(file.getPath());

				// Initialize call recording with path and file name
				call = new CallRecording(path, file.getName(), 0);

				// Add field generated automatically with a
				// 'filler' or a default value
				for (FieldFiller filler : mappingConfig.getFieldFiller()) {
					String value = filler.getOvalue();

					if (filler.getFiller() != null) {
						String callFullPath = call.getPathName() + File.separator + call.getFileName();
						value = ImportUtils.applyFiller(call, callFullPath, filler.getFiller());
					}

					call = ImportUtils.setFieldValueByFieldName(call, filler.getOname(), value);
				}

				recordings.put(call.getFileName(), call);
			}
		}

		return recordings;
	}
}
