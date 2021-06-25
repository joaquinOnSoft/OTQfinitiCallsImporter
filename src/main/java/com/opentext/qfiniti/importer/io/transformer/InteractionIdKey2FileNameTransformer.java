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
package com.opentext.qfiniti.importer.io.transformer;

public class InteractionIdKey2FileNameTransformer extends AbstractTransformer {

	private static final String CALL_RECORDING_FILE_PREFIX = "IRCall_";
	private static final String CALL_RECORDING_FILE_SUFIX = ".wav";

	public InteractionIdKey2FileNameTransformer(String path) {
		super(path);
	}
		
	/**
	 * Generates a call recording file name from an id, e.g. from and Id like
	 * 2001788444D0191021 will generate a file name like:
	 * 
	 * IRCall_2001788444D0191021.wav
	 * 
	 * @param id - Call recording id
	 * @return call recording file name
	 */
	@Override
	public String transform(String id) {
		StringBuilder builder = new StringBuilder();
		builder.append(CALL_RECORDING_FILE_PREFIX).append(id).append(CALL_RECORDING_FILE_SUFIX);

		return builder.toString();
	}

}
