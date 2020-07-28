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

public class XlsReader implements IReader {

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
	        
	        for (Row row: sheet) {
	        	
	        	//skip header row
	        	if(!isFirstRow) {
	        		call = new CallRecording();
	        		
	        		for(Cell cell: row) {		        		         
		            	switch(cell.getColumnIndex()) {		            	
		            	}
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

}
