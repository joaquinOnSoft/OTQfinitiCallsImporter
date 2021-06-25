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
package com.opentext.qfiniti.importer.io;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.pojo.MappingConfig;

public class CsvReader extends AbstractReader {

	/**
	 * Read a Comma-Separated Values (CSV) file which file is just a normal
	 * plain-text file, store data in column by column, and split it by a separator
	 * (e.g normally it is a comma "," or a ";").
	 * 
	 * @see http://zetcode.com/java/opencsv/
	 * @see https://www.baeldung.com/java-csv-file-array
	 * @see https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
	 */
	@Override
	public List<CallRecording> read(String filePath, MappingConfig config) {
		recordings = new LinkedList<CallRecording>();
		CallRecording call = null;

		try {
			Reader br = Files.newBufferedReader(Paths.get(filePath));
			CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
			CSVReader reader = new CSVReaderBuilder(br).withCSVParser(parser).build();

			String[] row;
			boolean isFirstRow = true;
			int index = 0;

			while ((row = reader.readNext()) != null) {
				// skip header row
				if (!isFirstRow) {
					call = new CallRecording();

					String parentFolder = (new File(filePath)).getParent();
					
					index = 0;
					for (int cell = 0; cell < row.length; cell++) {
						call = mapField(call, row[cell], config.getFieldMapping().get(index), parentFolder);

						index++;
					}

					call = generateField(call, config.getFieldFiller());

					recordings.add(call);
				}

				isFirstRow = false;
			}
		} catch (IOException | CsvValidationException e) {
			log.error(e.getMessage());
		}

		return recordings;
	}

}
