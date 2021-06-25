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
package com.opentext.qfiniti.importer.io.filler;

import java.io.File;

import com.opentext.qfiniti.importer.pojo.CallRecording;

public class FileNameFromFileFiller extends AbstractFiller {

	public FileNameFromFileFiller(CallRecording call, File file) {
		super(call, file);
	}

	public FileNameFromFileFiller(CallRecording call, String path, String fileName) {
		super(call, path, fileName);
	}

	public FileNameFromFileFiller(CallRecording call, String filePath) {
		super(call, filePath);
	}

	/**
	 * Returns the file name of the call recording file
	 */
	@Override
	public String getValue() {
		return file == null ? "" : file.getName();
	}
}
