package com.opentext.qfiniti.importer.io.filler;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opentext.qfiniti.importer.io.metadata.IMetadataCreator;
import com.opentext.qfiniti.importer.io.metadata.JaudiotaggerMetadataExtractor;
import com.opentext.qfiniti.importer.io.metadata.TikaMetadataExtractor;

public class DurationFromMetadataFiller extends AbstractFiller {
	private static final Logger log = LogManager.getLogger(DurationFromMetadataFiller.class);

	public DurationFromMetadataFiller(String path, String fileName) {
		super(path, fileName);
	}

	public DurationFromMetadataFiller(String filePath) {
		super(filePath);
	}

	public DurationFromMetadataFiller(File file) {
		super(file);
	}

	@Override
	public String getValue() {
		String duration = getDuration(new JaudiotaggerMetadataExtractor());		
		if (duration == null) {
			duration = getDuration(new TikaMetadataExtractor());
		} 

		return duration;
	}
	
	private String getDuration(IMetadataCreator extractor) {
		Map<String, String> metadata = null;
		String duration = null;
		
		try {
			metadata = extractor.extract(file);
		} catch (IOException e) {
			log.warn(e.getMessage());
		}
		
		if (metadata != null) {
			duration = metadata.get(IMetadataCreator.DURATION);
		}
		
		return duration;
	}

}
