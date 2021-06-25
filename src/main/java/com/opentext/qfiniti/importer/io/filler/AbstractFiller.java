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

public abstract class AbstractFiller {
	protected File file;
	protected CallRecording call;

	public AbstractFiller(CallRecording call, String path, String fileName) {
		this(call, path + File.separator + fileName);
	}

	public AbstractFiller(CallRecording call, String filePath) {
		this(call, new File(filePath));
	}

	public AbstractFiller(CallRecording call, File file) {
		this.call = call;
		this.file = file;
	}

	public abstract String getValue();

	protected int getRandom(int min, int max) {
		return (int) (Math.random() * ((max - min) + 1)) + min;
	}
}
