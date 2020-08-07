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

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.opentext.qfiniti.importer.io.ExcelWriter;
import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.pojo.MappingConfig;

/**
 * OpenText(TM) Qfiniti Importer Configuration Generator
 * @author Joaquín Garzón
 */
public abstract class AbstractQfinitiICG {
	protected static final Logger log = LogManager.getLogger(AbstractQfinitiICG.class);

	protected String path;
	protected String output;
	protected MappingConfig mappingConfig;


	public AbstractQfinitiICG(String path) {
		this.path = path;
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

	
	public MappingConfig getMappingConfig() {
		return mappingConfig;
	}

	public void setMappingConfig(MappingConfig mappingConfig) {
		this.mappingConfig = mappingConfig;
	}

	public List<CallRecording> generate() 
			throws IOException, InvalidFormatException {

		Map<String, CallRecording> recordings = new HashMap<String, CallRecording>();
		List<CallRecording> calls = null;

		recordings = generate(path, new HashMap<String, CallRecording>());

		if(recordings != null && recordings.size() > 0) { 
			calls = new LinkedList<CallRecording>(recordings.values());
			
			if(calls != null && calls.size() > 0) {
				ExcelWriter writter = new ExcelWriter();
				writter.write(mappingConfig.getColumnNames(), calls, output);
			}
		}

		return calls;
	}

	protected abstract Map<String, CallRecording> generate(String path, Map<String, CallRecording> recordings);
}
