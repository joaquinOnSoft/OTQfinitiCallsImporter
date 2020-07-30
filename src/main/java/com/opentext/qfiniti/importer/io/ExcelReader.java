package com.opentext.qfiniti.importer.io;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.opentext.qfiniti.importer.io.transformer.ITransformer;
import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.pojo.FieldMapping;
import com.opentext.qfiniti.importer.pojo.MappingConfig;

public class ExcelReader implements IReader {

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
								value = applyTransformer(transformerName, value);
							}							

							switch (fMapping.getOname()) {
							case COL_PATH_NAME:	
								call.setPathName(value);
								break;
							case COL_DATE_TIME:
								call.setDateTime(value);
								break;
							case COL_TEAM_MEMBER:
								call.setTeamMemberName(value);
								break;
							case COL_DURATION:
								call.setDuration(value);
								break;
							case COL_GROUP_HIERARCHY:
								call.setGroupHierachy(value);
								break;
							case COL_DNIS:
								call.setDnis(value);
								break;
							case COL_ANI:
								call.setAni(value);
								break;
							case COL_FILE_NAME :
								call.setFileName(value);
								break;
							}
						}
						else {
							call.addExtendedField(fMapping.getIname(), value);			            		
						}

						index++;
					}

					recordings.add(call);
				}

				isFirstRow = false;
			}

			// Closing the workbook
			workbook.close();			
		} catch (EncryptedDocumentException | IOException e) {
			System.err.println(e.getMessage());
		}

		return recordings.size() == 0? null : recordings;
	}


	private String applyTransformer(String transformerName, String value) {
		if(transformerName != null) {
			try {
				Class<?> tClass = Class.forName(transformerName);
				ITransformer itransformer = (ITransformer) tClass.getDeclaredConstructor().newInstance();
				value = itransformer.transform(value);
			} catch (ClassNotFoundException e) {
				System.err.print("Transformer class not found: " + e.getLocalizedMessage());
			} catch (NoSuchMethodException e) {
				System.err.print("Not 'transform' method in Transformer class: " + e.getLocalizedMessage());
			} catch (SecurityException e) {
				System.err.print("Invalid Transformer (1): " + e.getLocalizedMessage());
			} catch (InstantiationException e) {
				System.err.print("Invalid Transformer (2): " + e.getLocalizedMessage());
			} catch (IllegalAccessException e) {
				System.err.print("Invalid Transformer (3): " + e.getLocalizedMessage());
			} catch (IllegalArgumentException e) {
				System.err.print("Invalid Transformer (4): " + e.getLocalizedMessage());
			} catch (InvocationTargetException e) {
				System.err.print("Invalid Transformer (5): " + e.getLocalizedMessage());
			}
		}

		return value;
	}

}
