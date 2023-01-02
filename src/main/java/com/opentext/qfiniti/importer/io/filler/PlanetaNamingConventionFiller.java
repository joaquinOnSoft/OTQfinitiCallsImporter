package com.opentext.qfiniti.importer.io.filler;

import java.io.File;
import java.text.ParseException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.util.DateUtil;

/**
 * The file names have the following structure separated by hyphens:
 * <ul>
 *    <li>ID</li>
 *    <li>Date: ddmmaaaa_hhmmss</li>
 *    <li>Service</li>
 *    <li>Agent</li>
 *    <li>Duration</li>
 *    <li>Telephone: The first 4 digits correspond to a route to be ignored for processing.</li>
 * </ul>
 * File name example:
 * 
 * <code>
 *    13032476-31122021_143358-2622-5631-00307-0204697244857.gsm
 * </code>
 * 
 * Where:
 * <ul>
 *    <li><strong>ID:</strong> 13032476</li>
 *    <li><strong>Date:</strong> 31122021_143358</li>
 *    <li><strong>Service</strong> 2622</li>
 *    <li><strong>Agent</strong>5631</li>
 *    <li><strong>Duration</strong> 00307</li>
 *    <li><strong>Telephone:</strong>(0204) 697244857</li>
 * </ul>     
 * @author Joaquín Garzón 
 **/
public abstract class PlanetaNamingConventionFiller extends AbstractFiller {
	protected static final Logger log = LogManager.getLogger(PlanetaNamingConventionFiller.class);

	protected static final String FIELD_ID = "ID";
	protected static final String FIELD_DATE = "DATE";
	protected static final String FIELD_SERVICE = "SERVICE";
	protected static final String FIELD_AGENT = "AGENT";
	protected static final String FIELD_DURATION = "DURATION";
	protected static final String FIELD_ROUTE = "ROUTE";
	protected static final String FIELD_TELEPHONE = "TELEPHONE";

	protected static final int INDEX_ID = 0;
	protected static final int INDEX_DATE = 1;
	protected static final int INDEX_SERVICE = 2;
	protected static final int INDEX_AGENT = 3;
	protected static final int INDEX_DURATION = 4;
	protected static final int INDEX_TELEPHONE = 5;

	public PlanetaNamingConventionFiller(CallRecording call, String path, String fileName) {
		super(call, path, fileName);
	}

	public PlanetaNamingConventionFiller(CallRecording call, String filePath) {
		super(call, filePath);
	}

	public PlanetaNamingConventionFiller(CallRecording call, File file) {
		super(call, file);
	}

	protected String getField(String fieldName) {
		String value = null;

		String fileName = file.getName();

		if(fileName != null && fileName.compareTo("") != 0) {
			
			String[] matches = fileName.split("-");

			if(matches != null && matches.length == 6) {			
				switch(fieldName) {
				case FIELD_ID:
					value = matches[INDEX_ID];	
					break;			
				case FIELD_DATE:
					value = matches[INDEX_DATE];	

					try {
						//ddmmaaaa_hhmmss
						Date date = DateUtil.strToDate(value, "ddMMyyyy_HHmmss");
						value = DateUtil.dateToQfinitiFormat(date);
					} catch (ParseException e) {
						log.error("Expected format 'dd/MM/yyyy HH:mm:ss'. Value: " + value, e);			
					}

					break;			
				case FIELD_SERVICE:
					value = matches[INDEX_SERVICE];				
					break;			
				case FIELD_AGENT:
					value = matches[INDEX_AGENT];				
					break;			
				case FIELD_DURATION:
					value = matches[INDEX_DURATION];				
					break;			
				case FIELD_ROUTE:
					//The first 4 digits correspond to a route to be ignored for processing.
					value = matches[INDEX_TELEPHONE];				
					value = value.substring(0, 4);
					break;			
				case FIELD_TELEPHONE:
					//The first 4 digits correspond to a route to be ignored for processing.
					value = matches[INDEX_TELEPHONE];				
					value  = value.replace(".gsm", "").substring(4);
					break;
				}
			}
		}
		
		return value;
	}
}
