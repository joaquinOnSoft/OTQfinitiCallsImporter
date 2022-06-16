package com.opentext.qfiniti.importer.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PorpertiesCache {
	private static final Logger log = LogManager.getLogger(PorpertiesCache.class);

	private static PorpertiesCache instance;
	private Properties prop =null;

    private PorpertiesCache(String fileName) {
    	InputStream iStream = FileUtil.getStreamFromResources(fileName);
    	if(iStream != null) {
	    	prop = new Properties();
	    	try {
				prop.load(iStream);
			} catch (IOException e) {
				log.error("Error reading properties files: " + fileName, e);
			} 
    	}
    }

    public static PorpertiesCache getInstance(String fileName) {
        if (instance == null) {
            instance = new PorpertiesCache(fileName);
        }
        return instance;
    }
    
    public String getProperty(String name) {
    	String value = null;
    	
    	if(prop != null) {
    		value = (String) prop.get(name);
    	}
    	
    	return value;
    }
}
