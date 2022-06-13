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
 *     Joaqu�n Garz�n - initial implementation
 *
 */
package com.opentext.qfiniti.importer.io.transformer;

/**
 * Direction of the call. Must be a number 0-2. Valid values: 
 * <ul>
 *    <li>0 � Unknown</li>
 *    <li>1 � Inbound</li>
 *    <li>2 � Outbound</li>
 * </ul>   
 *
 */
public class RecordingId2FileNameTransformer extends AbstractTransformer {

	public RecordingId2FileNameTransformer(String path) {
		super(path);
	}

	/**
	 * Recording Identifier to call recording .wav file name
	 * @param recordingId - Recording unique identifier
	 */
	@Override
	public String transform(String recordingId) {					
		return recordingId + ".wav";
	}
}
