package com.opentext.qfiniti.importer.io;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.pojo.FieldFiller;
import com.opentext.qfiniti.importer.pojo.FieldMapping;
import com.opentext.qfiniti.importer.pojo.MappingConfig;
import com.opentext.qfiniti.importer.util.ImportUtils;

public class ExcelReader implements IReader {

	private static final Logger log = LogManager.getLogger(ExcelReader.class);

	/**
	 * Read the input file with the metadata for each call recording.
	 * @param filePath - Input file path (Excel with metadata for each call recording)
	 * @param config - Mapping configuration object
	 */
	@Override
	public List<CallRecording> read(String filePath, MappingConfig config) {
		List<CallRecording> recordings = new LinkedList<CallRecording>();
		CallRecording call = null;

		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook;
		try {
			workbook = WorkbookFactory.create(new File(filePath));

			// Getting the Sheet at index zero
			Sheet sheet = workbook.getSheetAt(0);

			boolean isFirstRow = true;

			int index = 0;
			FieldMapping fMapping =null;
			List<FieldFiller> fFillers = config.getFieldFiller();
			String transformerName = null;

			for (Row row: sheet) {

				//skip header row
				if(!isFirstRow) {
					call = new CallRecording();

					index = 0;
					for(Cell cell: row) {		 
						fMapping = config.getFieldMapping().get(index);

						String value = cell.getStringCellValue();

						if(fMapping.isMapped()) {
							transformerName = fMapping.getTransformer();
							if(transformerName != null) {
								value = ImportUtils.applyTransformer(transformerName, value);
							}							

							call = ImportUtils.setFieldValueByFieldName(call, fMapping.getOname(), value);
						}
						else {
							call.addExtendedField(fMapping.getIname(), value);			            		
						}

						index++;
					}
					
					// Add field generated automatically with a 
					// 'filler' or a default value
					for(FieldFiller filler: fFillers){
						String value = filler.getOvalue();
						
						if(filler.getFiller() != null) {
							String callFullPath = call.getPathName() + File.separator + call.getFileName();
							value = ImportUtils.applyFiller(filler.getFiller(), callFullPath);
						}
						
						call = ImportUtils.setFieldValueByFieldName(call, filler.getOname(), value);
					}
					
					recordings.add(call);
				}

				isFirstRow = false;
			}

			// Closing the workbook
			workbook.close();			
		} catch (EncryptedDocumentException | IOException e) {
			log.error(e.getMessage());
		}

		return recordings.size() == 0? null : recordings;
	}
}
