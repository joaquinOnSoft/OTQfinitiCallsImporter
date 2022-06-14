/*
 *   (C) Copyright 2022 OpenText and others.
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
import java.util.Map;

import com.opentext.qfiniti.importer.pojo.CallRecording;

public class ExtendedFields2UserDataFiller extends AbstractFiller {

	private static String SEPARATOR = "#";
	
	public ExtendedFields2UserDataFiller(CallRecording call, String path, String fileName) {
		super(call, path, fileName);
	}

	public ExtendedFields2UserDataFiller(CallRecording call, String filePath) {
		super(call, filePath);
	}

	public ExtendedFields2UserDataFiller(CallRecording call, File file) {
		super(call, file);
	}
	
	/**
	 * Generate "user_data" field based on extended fields values
	 **/
	@Override
	public String getValue() {
		StringBuffer value = null;
		
		Map<String, String> extendedFields = call.getExtendedFields();
		
		if(extendedFields != null && extendedFields.size() > 0) {
			value = new StringBuffer();
			
			for(String key: extendedFields.keySet()) {
				value.append(key)
					.append("=")
					.append(call.getExtendedField(key))
					.append(SEPARATOR);
			}
		}
		
		return value == null? null : value.toString();
	}
}
