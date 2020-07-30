package com.opentext.qfiniti.importer;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opentext.qfiniti.importer.pojo.MappingConfig;

public class JSonConfigReader {

	/**
	 * Read the configuration JSON file that defines the mapping between the 
	 * 3rd party excel including metadata and the input file required by 
	 * OpenText Qfiniti Data Importer
	 * @see JSON to Java Object. 
	 * https://www.baeldung.com/jackson-object-mapper-tutorial#2-json-to-java-object
	 * @param file - JSON File object
	 * @return Object with the field mapping
	 */
	public MappingConfig read(File file) {
		MappingConfig config = null;
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			config = objectMapper.readValue(file, MappingConfig.class);
		} catch (IOException e) {
			System.err.print(e.getMessage());			
		}
		
		return config;
	}	
	
	/**
	 * Read the configuration JSON file that defines the mapping between the 
	 * 3rd party excel including metadata and the input file required by 
	 * OpenText Qfiniti Data Importer
	 * @see JSON to Java Object. 
	 * https://www.baeldung.com/jackson-object-mapper-tutorial#2-json-to-java-object
	 * @param filename - File Name
	 * @return Object with the field mapping
	 */
	public MappingConfig read(String filename) {
		return read(new File(filename));
	}
}
