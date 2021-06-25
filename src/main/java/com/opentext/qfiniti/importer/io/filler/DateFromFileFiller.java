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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opentext.qfiniti.importer.pojo.CallRecording;

public class DateFromFileFiller extends AbstractFiller {
	private static final Logger log = LogManager.getLogger(DateFromFileFiller.class);

	public DateFromFileFiller(CallRecording call, String path, String fileName) {
		super(call, path, fileName);
	}

	public DateFromFileFiller(CallRecording call, String filePath) {
		super(call, filePath);
	}

	public DateFromFileFiller(CallRecording call, File file) {
		super(call, file);
	}

	/**
	 * NOTE: The NIP API is returning the last access time instead of the creation
	 * date!!!
	 * 
	 * @see https://www.baeldung.com/java-file-creation-date
	 * @see https://mkyong.com/java/how-to-get-the-file-creation-date-in-java/
	 */
	@Override
	public String getValue() {
		String strDate = null;

		try {

			BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			FileTime time = attr.creationTime();

			Date creationDate = new Date(time.toMillis());

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			strDate = dateFormat.format(creationDate);
		} catch (IOException e) {
			log.error("File: " + file.getPath() + ": ", e);
		}

		return strDate;
	}
}
