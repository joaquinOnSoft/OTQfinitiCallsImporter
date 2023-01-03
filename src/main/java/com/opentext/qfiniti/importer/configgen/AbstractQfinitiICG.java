/*
 *   
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
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.opentext.qfiniti.importer.io.ExcelWriter;
import com.opentext.qfiniti.importer.io.filter.AudioFilter;
import com.opentext.qfiniti.importer.io.filter.FolderFilter;
import com.opentext.qfiniti.importer.io.filter.WavFilter;
import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.pojo.MappingConfig;

/**
 * OpenText(TM) Qfiniti Importer Configuration Generator
 * 
 * @author Joaquín Garzón
 */
public abstract class AbstractQfinitiICG {
	protected static final Logger log = LogManager.getLogger(AbstractQfinitiICG.class);

	protected String path;
	protected String extension;
	protected String output;
	protected MappingConfig mappingConfig;
	protected boolean allAudioFormats;

	public AbstractQfinitiICG(String path) {
		this(path, null);
	}

	public AbstractQfinitiICG(String path, String extension) {
		this(path, extension, false);
	}
	
	public AbstractQfinitiICG(String path, String extension, boolean allAudioFormats) {
		this.path = path;
		this.extension = extension;
		this.allAudioFormats = allAudioFormats;
	}	

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getOutput() {
		return output;
	}

	public boolean isAllAudioFormats() {
		return allAudioFormats;
	}

	public void setAllAudioFormats(boolean allAudioFormats) {
		this.allAudioFormats = allAudioFormats;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public AbstractQfinitiICG(boolean allAudioFormats) {
		super();
		this.allAudioFormats = allAudioFormats;
	}

	public MappingConfig getMappingConfig() {
		return mappingConfig;
	}

	public void setMappingConfig(MappingConfig mappingConfig) {
		this.mappingConfig = mappingConfig;
	}

	public List<CallRecording> generate() throws IOException, InvalidFormatException {

		Map<String, CallRecording> recordings = new HashMap<String, CallRecording>();
		List<CallRecording> calls = null;

		recordings = generate(path, new HashMap<String, CallRecording>());

		if (recordings != null && recordings.size() > 0) {
			calls = new LinkedList<CallRecording>(recordings.values());

			if (calls != null && calls.size() > 0) {
				ExcelWriter writter = new ExcelWriter();
				writter.write(mappingConfig.getColumnNames(), calls, output);
			}
		}

		return calls;
	}

	protected Map<String, CallRecording> generate(String path, Map<String, CallRecording> recordings) {
		// Read data files (.json, .xls or .csv)
		recordings = readDataFiles(path, recordings);

		// Read audio files (.wav) or all formats (.wav, .gsm, .mp3, .ogg) 
		recordings = readAudioFiles(path, recordings);

		// Find sub-folders
		recordings = findSubfolders(path, recordings);

		return recordings;
	}

	/**
	 * Read Metadata files (.xls, .csv, .json)
	 * 
	 * @param path
	 * @param recordings
	 * @return
	 */
	protected abstract Map<String, CallRecording> readDataFiles(String path, Map<String, CallRecording> recordings);

	/**
	 * Read audio files (.wav) or all formats (.wav, .gsm, .mp3, .ogg) 
	 * @param path
	 * @param recordings
	 * @return
	 */
	protected Map<String, CallRecording> readAudioFiles(String path, Map<String, CallRecording> recordings) {	
		File audioFiles[] = null;
		
		if(allAudioFormats) {
			// Read audio files (.wav, .gsm, .mp3, .ogg)
			AudioFilter wavfilter = new AudioFilter();
			audioFiles = wavfilter.finder(path);			
		}
		else {
			// Read audio files (.wav)			
			WavFilter wavfilter = new WavFilter();
			audioFiles = wavfilter.finder(path);
		}
				
		if (audioFiles != null && audioFiles.length > 0) {
			CallRecording call = null;
			for (File file : audioFiles) {
				log.info(file.getPath());

				call = recordings.get(file.getName());

				if (call != null) {
					try {
						call.setPathName(file.getParentFile().getCanonicalPath());
						recordings.put(call.getFileName(), call);
					} catch (Exception e) {
						log.error(path + " --^-- " + e.getMessage());
					}
				}
			}
		}

		return recordings;
	}

	/**
	 * Find sub-folders
	 * 
	 * @param path
	 * @param recordings
	 * @return
	 */
	protected Map<String, CallRecording> findSubfolders(String path, Map<String, CallRecording> recordings) {
		FolderFilter folderfilter = new FolderFilter();

		File folders[] = folderfilter.finder(path);
		if (folders != null && folders.length > 0) {

			for (File folder : folders) {
				log.debug(folder.getPath());
				recordings = generate(folder.getPath(), recordings);
			}
		}

		return recordings;
	}
}
