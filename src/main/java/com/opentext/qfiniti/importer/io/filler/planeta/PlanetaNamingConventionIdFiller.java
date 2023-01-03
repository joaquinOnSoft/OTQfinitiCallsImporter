package com.opentext.qfiniti.importer.io.filler.planeta;

import java.io.File;

import com.opentext.qfiniti.importer.pojo.CallRecording;

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
 */ 
public class PlanetaNamingConventionIdFiller extends PlanetaNamingConventionFiller {
		
	public PlanetaNamingConventionIdFiller(CallRecording call, String path, String fileName) {
		super(call, path, fileName);
	}

	public PlanetaNamingConventionIdFiller(CallRecording call, String filePath) {
		super(call, filePath);
	}

	public PlanetaNamingConventionIdFiller(CallRecording call, File file) {
		super(call, file);
	}
	
	
	@Override
	public String getValue() {
		return getField(FIELD_ID);
	}
}
