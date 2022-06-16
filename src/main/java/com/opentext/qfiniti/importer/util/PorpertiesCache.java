package com.opentext.qfiniti.importer.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    	InputStream iStream = null;
    	
		try {
			iStream = FileUtil.getStreamFromResources(fileName);
		} catch (IllegalArgumentException e) {
			log.info("Error reading properties file from resources: " + fileName, e);
		}
		
    	if(iStream != null) {
    		log.debug("Reading properties from resources/CLASSPATH");
    		
	    	prop = new Properties();
	    	try {
				prop.load(iStream);
			} catch (IOException e) {
				log.error("Error reading properties files: " + fileName, e);
			} 
    	}
    	else {			
    		log.debug("Trying to read properties from working directory");

    		String workingDirectory = System.getProperty("user.dir");
    		log.debug("Working directory: " + workingDirectory);
    		
    		File propertiesFile = new File(workingDirectory + System.getProperty("file.separator") + fileName);
    		log.debug("Properties file full path: " + propertiesFile.getAbsolutePath());    		
    		
    		prop = new Properties();    		
    		try {
				prop.load(new FileReader(propertiesFile));
			} catch (FileNotFoundException e) {
				log.error("Error reading properties files at " + workingDirectory + ": " + fileName, e);
			} catch (IOException e) {
				log.error("Error reading properties files at" + workingDirectory + ": " + fileName, e);
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
