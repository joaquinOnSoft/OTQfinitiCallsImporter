/*
 *   (C) Copyright 2023 OpenText and others.
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
package com.opentext.qfiniti.importer.io.filler.planeta;

import java.io.File;

import com.opentext.qfiniti.importer.pojo.CallRecording;

/**
 * The file names have the following structure separated by hyphens:
 * <ul>
 *    <li>ID</li>
 *    <li>Date: ddmmaaaa_hhmmss</li>
 *    <li>Service</li>
 *    <li>Agent</li>
 *    <li>Duration</li>
 *    <li>Telephone: The first 4 digits correspond to a route to be ignored for processing.</li>
 * </ul>
 */ 
public class PlanetaNamingConventionDurationFiller extends PlanetaNamingConventionFiller {
		
	public PlanetaNamingConventionDurationFiller(CallRecording call, String path, String fileName) {
		super(call, path, fileName);
	}

	public PlanetaNamingConventionDurationFiller(CallRecording call, String filePath) {
		super(call, filePath);
	}

	public PlanetaNamingConventionDurationFiller(CallRecording call, File file) {
		super(call, file);
	}
	
	
	@Override
	public String getValue() {
		return getField(FIELD_DURATION);
	}
}
