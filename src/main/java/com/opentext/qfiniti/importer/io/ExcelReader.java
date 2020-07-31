package com.opentext.qfiniti.importer.io;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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

import com.opentext.qfiniti.importer.io.filler.IFiller;
import com.opentext.qfiniti.importer.io.transformer.ITransformer;
import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.pojo.FieldFiller;
import com.opentext.qfiniti.importer.pojo.FieldMapping;
import com.opentext.qfiniti.importer.pojo.MappingConfig;

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
								value = applyTransformer(transformerName, value);
							}							

							call = setFieldValueByFieldName(call, fMapping.getOname(), value);
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
							value = applyFiller(filler.getFiller());
						}
						
						call = setFieldValueByFieldName(call, filler.getOname(), value);
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


	/**
	 * Assigns a value in a field based on the field Name
	 * @param call - Call recording object
	 * @param fieldName - Field name
	 * @param value - Value to set in the field
	 * @return Call recording object with the field populated
	 */
	private CallRecording setFieldValueByFieldName(CallRecording call, String fieldName, String value) {
		switch (fieldName) {
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
		
		return call;
	}


	private String applyTransformer(String transformerName, String value) {
		if(transformerName != null) {
			try {
				Class<?> tClass = Class.forName(transformerName);
				ITransformer itransformer = (ITransformer) tClass.getDeclaredConstructor().newInstance();
				value = itransformer.transform(value);
			} catch (ClassNotFoundException e) {
				log.error("Transformer class not found: " + e.getLocalizedMessage());
			} catch (NoSuchMethodException e) {
				log.error("Not 'transform' method in Transformer class: " + e.getLocalizedMessage());
			} catch (SecurityException e) {
				log.error("Invalid Transformer (1): " + e.getLocalizedMessage());
			} catch (InstantiationException e) {
				log.error("Invalid Transformer (2): " + e.getLocalizedMessage());
			} catch (IllegalAccessException e) {
				log.error("Invalid Transformer (3): " + e.getLocalizedMessage());
			} catch (IllegalArgumentException e) {
				log.error("Invalid Transformer (4): " + e.getLocalizedMessage());
			} catch (InvocationTargetException e) {
				log.error("Invalid Transformer (5): " + e.getLocalizedMessage());
			}
		}

		return value;
	}

	private String applyFiller(String fillerName) {
		String value = null;
		
		if(fillerName != null) {
			try {
				Class<?> tClass = Class.forName(fillerName);
				IFiller ifiller = (IFiller) tClass.getDeclaredConstructor().newInstance();
				value = ifiller.getValue();
			} catch (ClassNotFoundException e) {
				log.error("Filler class not found: " + e.getLocalizedMessage());
			} catch (NoSuchMethodException e) {
				log.error("Not 'getValue' method in Filler class: " + e.getLocalizedMessage());
			} catch (SecurityException e) {
				log.error("Invalid Filler (1): " + e.getLocalizedMessage());
			} catch (InstantiationException e) {
				log.error("Invalid Filler (2): " + e.getLocalizedMessage());
			} catch (IllegalAccessException e) {
				log.error("Invalid Filler (3): " + e.getLocalizedMessage());
			} catch (IllegalArgumentException e) {
				log.error("Invalid Filler (4): " + e.getLocalizedMessage());
			} catch (InvocationTargetException e) {
				log.error("Invalid Filler (5): " + e.getLocalizedMessage());
			}
		}

		return value;
	}
	
	
}
