package com.opentext.qfiniti.importer.io;

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

					index = 0;

					for (int cell = 0; cell < row.length; cell++) {
						call = mapField(call, row[cell], config.getFieldMapping().get(index), filePath);

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
