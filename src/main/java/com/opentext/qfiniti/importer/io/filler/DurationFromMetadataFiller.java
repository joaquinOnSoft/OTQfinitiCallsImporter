package com.opentext.qfiniti.importer.io.filler;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opentext.qfiniti.importer.io.metadata.IMetadataCreator;
import com.opentext.qfiniti.importer.io.metadata.JaudiotaggerMetadataExtractor;
import com.opentext.qfiniti.importer.io.metadata.JavaMetadataExtractor;
import com.opentext.qfiniti.importer.io.metadata.TikaMetadataExtractor;
import com.opentext.qfiniti.importer.pojo.CallRecording;

public class DurationFromMetadataFiller extends AbstractFiller {
	private static final Logger log = LogManager.getLogger(DurationFromMetadataFiller.class);

	public DurationFromMetadataFiller(CallRecording call, String path, String fileName) {
		super(call, path, fileName);
	}

	public DurationFromMetadataFiller(CallRecording call, String filePath) {
		super(call, filePath);
	}

	public DurationFromMetadataFiller(CallRecording call, File file) {
		super(call, file);
	}

	@Override
	public String getValue() {
		String duration = getDuration(new JaudiotaggerMetadataExtractor());
		if (duration == null) {
			duration = getDuration(new TikaMetadataExtractor());
		} 
		if (duration == null) {
			duration = getDuration(new JavaMetadataExtractor());
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
