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

import com.opentext.qfiniti.helper.DateHelper;
import com.opentext.qfiniti.importer.pojo.CallRecording;

/**
 * 
 * @author Joaquín Garzón
 * @see https://www.callicoder.com/java-read-excel-file-apache-poi/
 */
public class ExcelReader {


	
	
	/** Column title: Media Type */
	public static final String COL_TITLE_MEDIA_TYPE = "Media Type";
	/** Column title: Recording Type */
	public static final String COL_TITLE_RECORDING_TYPE = "Recording Type";	
	/** Column title: Recording ID */
	public static final String COL_TITLE_RECORDING_ID = "Recording ID";
	/** Column title: Date/Time */
	public static final String COL_TITLE_DATE_TIME = "Date/Time";
	/** Column title: Recording Length */
	public static final String COL_TITLE_RECORDING_LENGTH = "Recording Length";
	/** Column title: Direction */
	public static final String COL_TITLE_DIRECTION = "Direction";
	/** Column title: Initiator Interaction Address */
	public static final String COL_TITLE_INITIATOR_INTERACTION_ADDRESS = "Initiator Interaction Address";
	/** Column title: Interaction Address */
	public static final String COL_TITLE_INTERACTION_ADDRESS = "Interaction Address";
	/** Column title: Local Party Name */
	public static final String COL_TITLE_LOCAL_PARTY_NAME = "Local Party Name";
	/** Column title: Scoring User */
	public static final String COL_TITLE_SCORING_USER = "Scoring User";
	/** Column title: Scoring Status */
	public static final String COL_TITLE_SCORING_STATUS = "Scoring Status";
	/** Column title: Queue */
	public static final String COL_TITLE_QUEUE = "Queue";
	/** Column title: Interaction ID Key */
	public static final String COL_TITLE_INTERACTION_ID_KEY = "Interaction ID Key";
	/** Column title: Initiation Policy */
	public static final String COL_TITLE_INITIATION_POLICY = "Initiation Policy";
	/** Column title: Related Recordings */
	public static final String COL_TITLE_RELATED_RECORDINGS = "Related Recordings";
	/** Column title: Customer Keyword Score */
	public static final String COL_TITLE_CUSTORMER_KEYWORD_SCORE = "Customer Keyword Score";
	/** Column title: Agent Keyword Score */
	public static final String COL_TITLE_AGENT_KEYWORD_SCORE = "Agent Keyword Score";
	/** Column title: Total Keyword Score */
	public static final String COL_TITLE_TOTAL_KEYWORD_SCORE = "Total Keyword Score";
		
	

	/** Column Index: Media Type */
	public static final int COL_INDEX_00_MEDIA_TYPE = 0;
	/** Column Index: Recording Type */
	public static final int COL_INDEX_01_RECORDING_TYPE = 1;	
	/** Column Index: Recording ID */
	public static final int COL_INDEX_02_RECORDING_ID = 2;
	/** Column Index: Date/Time */
	public static final int COL_INDEX_03_DATE_TIME = 3;
	/** Column Index: Recording Length */
	public static final int COL_INDEX_04_RECORDING_LENGTH = 4;
	/** Column Index: Direction */
	public static final int COL_INDEX_05_DIRECTION = 5;
	/** Column Index: Initiator Interaction Address */
	public static final int COL_INDEX_06_INITIATOR_INTERACTION_ADDRESS = 6;
	/** Column Index: Interaction Address */
	public static final int COL_INDEX_07_INTERACTION_ADDRESS = 7;
	/** Column Index: Local Party Name */
	public static final int COL_INDEX_08_LOCAL_PARTY_NAME = 8;
	/** Column Index: Scoring User */
	public static final int COL_INDEX_09_SCORING_USER = 9;
	/** Column Index: Scoring Status */
	public static final int COL_INDEX_10_SCORING_STATUS = 10;
	/** Column Index: Queue */
	public static final int COL_INDEX_11_QUEUE = 11;
	/** Column Index: Interaction ID Key */
	public static final int COL_INDEX_12_INTERACTION_ID_KEY = 12;
	/** Column Index: Initiation Policy */
	public static final int COL_INDEX_13_INITIATION_POLICY = 13;
	/** Column Index: Related Recordings */
	public static final int COL_INDEX_14_RELATED_RECORDINGS = 14;
	/** Column Index: Customer Keyword Score */
	public static final int COL_INDEX_15_CUSTORMER_KEYWORD_SCORE = 15;
	/** Column Index: Agent Keyword Score */
	public static final int COL_INDEX_16_AGENT_KEYWORD_SCORE = 16;
	/** Column Index: Total Keyword Score */
	public static final int COL_INDEX_17_TOTAL_KEYWORD_SCORE = 17;
	
	
	public List<CallRecording> read(String filePath){
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
			            	case  COL_INDEX_00_MEDIA_TYPE:
			            		call.addExtendedField(COL_TITLE_MEDIA_TYPE, cell.getStringCellValue());
			            		break;
			            	case  COL_INDEX_01_RECORDING_TYPE:
			            		call.addExtendedField(COL_TITLE_RECORDING_TYPE, cell.getStringCellValue());			            		
			            		break;
			            	case  COL_INDEX_02_RECORDING_ID:	
			            		call.addExtendedField(COL_TITLE_RECORDING_ID, cell.getStringCellValue());			            		
			            		break;
			            	case  COL_INDEX_03_DATE_TIME:	
			            		call.setDateTime(DateHelper.strToDate(cell.getStringCellValue(), DateHelper.DATE_FORMAT_IBERDROLA));
		            			break;
			            	case  COL_INDEX_04_RECORDING_LENGTH:
			            		call.setDuration(DateHelper.hoursMinutesSecondsToSeconds(cell.getStringCellValue()));
			            		break;
			            	case  COL_INDEX_05_DIRECTION:
			            		call.addExtendedField(COL_TITLE_DIRECTION, cell.getStringCellValue());			            		
			            		break;
			            	case  COL_INDEX_06_INITIATOR_INTERACTION_ADDRESS:
			            		call.setAni(cell.getStringCellValue());
			            		break;
			            	case  COL_INDEX_07_INTERACTION_ADDRESS:
			            		call.setDnis(cell.getStringCellValue());
			            		break;
			            	case  COL_INDEX_08_LOCAL_PARTY_NAME:
			            		String name = cell.getStringCellValue();
			            		if(name == null || name.compareTo("") == 0) {
			            			call.setDefaultTeamMemberName();
			            		}
			            		else {
			            			call.setTeamMemberName(name);
			            		}
			            		//call.addExtendedField(COL_TITLE_LOCAL_PARTY_NAME, agent);			            		
			            		break;
			            	case  COL_INDEX_09_SCORING_USER:
			            		call.addExtendedField(COL_TITLE_SCORING_USER, cell.getStringCellValue());			            		
			            		break;
			            	case  COL_INDEX_10_SCORING_STATUS:
			            		call.addExtendedField(COL_TITLE_SCORING_STATUS, cell.getStringCellValue());			            		
			            		break;
			            	case  COL_INDEX_11_QUEUE:
			            		call.addExtendedField(COL_TITLE_QUEUE, cell.getStringCellValue());			            		
			            		break;
			            	case  COL_INDEX_12_INTERACTION_ID_KEY:
			            		call.addExtendedField(COL_TITLE_INTERACTION_ID_KEY, cell.getStringCellValue());
			            		//call.setFileName(IberdrolaHelper.generateNameFromId(cell.getStringCellValue()));			            					            	
			            		break;
			            	case  COL_INDEX_13_INITIATION_POLICY:
			            		call.addExtendedField(COL_TITLE_INITIATION_POLICY, cell.getStringCellValue());			            		
			            		break;
			            	case  COL_INDEX_14_RELATED_RECORDINGS:
			            		call.addExtendedField(COL_TITLE_RELATED_RECORDINGS, cell.getStringCellValue());			            		
			            		break;
			            	case  COL_INDEX_15_CUSTORMER_KEYWORD_SCORE:
			            		call.addExtendedField(COL_TITLE_CUSTORMER_KEYWORD_SCORE, cell.getStringCellValue());			            		
		            			break;
			            	case  COL_INDEX_16_AGENT_KEYWORD_SCORE:
			            		call.addExtendedField(COL_TITLE_AGENT_KEYWORD_SCORE, cell.getStringCellValue());			            		
			            		break;
			            	case  COL_INDEX_17_TOTAL_KEYWORD_SCORE:
			            		call.addExtendedField(COL_TITLE_TOTAL_KEYWORD_SCORE, cell.getStringCellValue());			            		
			            		break; 	
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
