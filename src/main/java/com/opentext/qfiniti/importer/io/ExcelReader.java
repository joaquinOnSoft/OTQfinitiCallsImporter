package com.opentext.qfiniti.importer.io;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.pojo.MappingConfig;

public class ExcelReader extends AbstractReader {

	/**
	 * Read the input file with the metadata for each call recording.
	 * 
	 * @param filePath - Input file path (Excel with metadata for each call
	 *                 recording)
	 * @param config   - Mapping configuration object
	 */
	@Override
	public List<CallRecording> read(String filePath, MappingConfig config) {
		recordings = new LinkedList<CallRecording>();
		CallRecording call = null;

		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook;
		try {
			workbook = WorkbookFactory.create(new File(filePath));

			// Getting the Sheet at index zero
			Sheet sheet = workbook.getSheetAt(0);

			boolean isFirstRow = true;

			int index = 0;

			for (Row row : sheet) {

				// skip header row
				if (!isFirstRow) {
					call = new CallRecording();

					index = 0;
					for (Cell cell : row) {
						call = mapField(call, cell.getStringCellValue(), config.getFieldMapping().get(index), filePath);

						index++;
					}

					call = generateField(call, config.getFieldFiller());

					recordings.add(call);
				}

				isFirstRow = false;
			}

			// Closing the workbook
			workbook.close();
		} catch (EncryptedDocumentException | IOException e) {
			log.error(e.getMessage());
		}

		return recordings.size() == 0 ? null : recordings;
	}
}
