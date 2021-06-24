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

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.opentext.qfiniti.importer.pojo.CallRecording;

/**
 * SEE: https://www.callicoder.com/java-write-excel-file-apache-poi/
 * 
 * @author Joaquín Garzón
 */
public class ExcelWriter {

	public void write(String[] columns, List<CallRecording> recordings, String outputFileName)
			throws IOException, InvalidFormatException {
		// Create a Workbook
		// Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating
		// `.xls` file
		Workbook workbook = new HSSFWorkbook(); // new XSSFWorkbook() for generating `.xlsx` file

		/*
		 * CreationHelper helps us create instances of various things like DataFormat,
		 * Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way
		 */
		// CreationHelper createHelper = workbook.getCreationHelper();

		// Create a Sheet
		Sheet sheet = workbook.createSheet("Sheet1");

		// Create a Row
		Row headerRow = sheet.createRow(0);

		// Create cells
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			// cell.setCellStyle(headerCellStyle);
		}

		// Create cell style to date cells
		CellStyle cellStyle = workbook.createCellStyle();
		CreationHelper createHelper = workbook.getCreationHelper();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd/mm/yyyy hh:mm:ss"));

		// Create Other rows and cells with employees data
		int rowNum = 1;
		int maxNumCol = columns.length;
		int col = 0;
		String columnName = null;
		
		for (CallRecording recording : recordings) {
			Row row = sheet.createRow(rowNum++);

			for (int nCol = 0; nCol < maxNumCol; nCol++) {
				Cell cell = row.createCell(col);

				String value = null;
				columnName = columns[nCol];
				switch (columnName) {
				case CallRecording.HEADER_PATH_NAME:
					value = recording.getPathName();
					break;
				case CallRecording.HEADER_DATE_TIME:
					cell.setCellValue(recording.getDateTime());
					cell.setCellStyle(cellStyle);

					break;
				case CallRecording.HEADER_TEAM_MEMBER_NAME:
					value = recording.getTeamMemberName();
					break;
				case CallRecording.HEADER_DURATION:
					value = recording.getDurationAsString();
					break;
				case CallRecording.HEADER_GROUP_HIERARCHY:
					value = recording.getGroupHierachy();
					break;
				case CallRecording.HEADER_FILE_NAME:
					value = recording.getFileName();
					break;
				case CallRecording.HEADER_ANI:
					value = recording.getAni();
					break;
				case CallRecording.HEADER_DNIS:
					value = recording.getDnis();
					break;
				default:
					value = recording.getExtendedField(columnName);

				}

				if (value == null) { 
					value = "";
				}				
				
				cell.setCellValue(value);

				col++;
			}

			col = 0;
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(outputFileName);
		workbook.write(fileOut);
		fileOut.close();

		// Closing the workbook
		workbook.close();
	}
}
