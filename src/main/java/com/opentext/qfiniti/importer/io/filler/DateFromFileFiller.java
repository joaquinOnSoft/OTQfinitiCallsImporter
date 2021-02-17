package com.opentext.qfiniti.importer.io.filler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateFromFileFiller extends AbstractFiller {
	private static final Logger log = LogManager.getLogger(DateFromFileFiller.class);

	public DateFromFileFiller(String path, String fileName) {
		super(path, fileName);
	}

	public DateFromFileFiller(String filePath) {
		super(filePath);
	}

	public DateFromFileFiller(File file) {
		super(file);
	}

	/**
	 * NOTE: The NIP API is returning the last access time instead of the creation
	 * date!!!
	 * 
	 * @see https://www.baeldung.com/java-file-creation-date
	 * @see https://mkyong.com/java/how-to-get-the-file-creation-date-in-java/
	 */
	@Override
	public String getValue() {
		String strDate = null;

		try {

			BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			FileTime time = attr.creationTime();

			Date creationDate = new Date(time.toMillis());

			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			strDate = dateFormat.format(creationDate);
		} catch (IOException e) {
			log.error("File: " + file.getPath() + ": ", e);
		}

		return strDate;
	}
}
