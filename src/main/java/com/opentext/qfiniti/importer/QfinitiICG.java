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
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.opentext.qfiniti.importer.io.ExcelReader;
import com.opentext.qfiniti.importer.io.ExcelWritter;
import com.opentext.qfiniti.importer.io.filter.FolderFilter;
import com.opentext.qfiniti.importer.io.filter.WavFilter;
import com.opentext.qfiniti.importer.io.filter.XlsFilter;
import com.opentext.qfiniti.importer.pojo.CallRecording;

/**
 * OpenText(TM) Qfiniti Importer Configuration Generator
 * @author Joaquín Garzón
 */
public class QfinitiICG {
	private static final Logger log = LogManager.getLogger(QfinitiICG.class);

	private static final String FIELD_INTERACTION_ID_KEY = "Interaction ID Key";

	private String path;
	private String output;
	private boolean agent;


	public QfinitiICG(String path) {
		this.path = path;
	}
	
	public boolean isAgent() {
		return agent;
	}

	public void setAgent(boolean agent) {
		this.agent = agent;
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

	public void setOutput(String output) {
		this.output = output;
	}

	public List<CallRecording> generate() 
			throws IOException, InvalidFormatException {

		Map<String, CallRecording> recordings = new HashMap<String, CallRecording>();
		List<CallRecording> calls = null;



		recordings = generate(path, new HashMap<String, CallRecording>());

		if(recordings != null && recordings.size() > 0) { 
			calls = new LinkedList<CallRecording>(recordings.values());
			
			if(calls != null && calls.size() > 0) {
				String[] headers = null;
				
				if(agent) {
					headers = calls.get(0).getHeaders();
				}
				else {
					List<String> excludedHeaders = new LinkedList<String>();
					excludedHeaders.add(CallRecording.HEADER_TEAM_MEMBER_NAME);
					
					headers = calls.get(0).getHeaders(excludedHeaders);
				}
				
				ExcelWritter writter = new ExcelWritter();
				writter.write(headers, calls, output);
			}
		}

		return calls;
	}

	private Map<String, CallRecording> generate(String path, Map<String, CallRecording> recordings) {
		XlsFilter xlsfilter = new XlsFilter();
		FolderFilter folderfilter = new FolderFilter();
		WavFilter wavfilter = new WavFilter();

		//Read Excel files
		File xlsFiles[] = xlsfilter.finder(path);
		if(xlsFiles != null && xlsFiles.length >0) {
			ExcelReader reader = new ExcelReader();
			for (File file : xlsFiles) {
				//TODO add 2nd parameter
				List<CallRecording> tmpRecordings = reader.read(file.getAbsolutePath(), null);
				recordings = dumpListToMap(recordings, tmpRecordings);
			}			
		}

		//Read Excel files
		File wavFiles[] = wavfilter.finder(path);
		if(wavFiles != null && wavFiles.length >0) {

			CallRecording call = null;
			String id = null;
			for (File file : wavFiles) {
				log.info(file.getPath());

				//id = IberdrolaHelper.getIdFromFileName(file.getName());
				//call = recordings.get(id);
				
				if(call != null) {
					try {
						call.setPathName(file.getParentFile().getCanonicalPath());
						recordings.put(id, call);
					} catch (Exception e) {
						System.err.println(path + " --^-- " + e.getMessage());
					}

				}
			}			
		}	

		//Read Excel files
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
			map.put(callRecording.getExtendedField(FIELD_INTERACTION_ID_KEY), callRecording);
		}

		return map;
	}

}
