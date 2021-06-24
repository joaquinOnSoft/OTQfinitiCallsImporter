package com.opentext.qfiniti.importer.io.transformer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractTransformer {
	protected static final Logger log = LogManager.getLogger(AbstractTransformer.class);

	public static final String DATE_FORMAT_QFINITI = "dd/MM/yyyy HH:mm:ss";
	
	protected String path;
	
	public AbstractTransformer(String path){
		this.path = path;
	}
	
	public abstract String transform(String input);
}
