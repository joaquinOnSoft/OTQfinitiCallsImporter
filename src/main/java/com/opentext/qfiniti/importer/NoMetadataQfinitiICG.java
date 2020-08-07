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

import java.io.File;
import java.util.List;
import java.util.Map;

import com.opentext.qfiniti.importer.io.filter.FolderFilter;
import com.opentext.qfiniti.importer.io.filter.WavFilter;
import com.opentext.qfiniti.importer.pojo.CallRecording;

/**
 * OpenText(TM) Qfiniti Importer Configuration Generator
 * @author Joaquín Garzón
 */
public class NoMetadataQfinitiICG extends AbstractQfinitiICG{

	public NoMetadataQfinitiICG(String path) {
		super(path);
	}

	protected Map<String, CallRecording> generate(String path, Map<String, CallRecording> recordings) {
		FolderFilter folderfilter = new FolderFilter();
		WavFilter wavfilter = new WavFilter();


		//Read audio files (.wav)
		File wavFiles[] = wavfilter.finder(path);
		if(wavFiles != null && wavFiles.length >0) {

			CallRecording call = null;
			for (File file : wavFiles) {
				log.info(file.getPath());
				
				call = recordings.get(file.getName());
				
				if(call != null) {
					try {
						call.setPathName(file.getParentFile().getCanonicalPath());
						recordings.put(call.getFileName(), call);
					} catch (Exception e) {
						log.error(path + " --^-- " + e.getMessage());
					}
				}
			}			
		}	

		//Find sub-folders
		File folders[] = folderfilter.finder(path);
		if(folders != null && folders.length >0) {

			for (File folder : folders) {
				log.debug(folder.getPath());
				recordings = generate(folder.getPath(), recordings);
			}			
		}			

		return recordings;
	}

	private Map<String, CallRecording> dumpListToMap(Map<String, CallRecording> map, List<CallRecording> list){
		for (CallRecording callRecording : list) {
			map.put(callRecording.getFileName(), callRecording);
		}

		return map;
	}

}
