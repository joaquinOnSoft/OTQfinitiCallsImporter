package com.opentext.qfiniti.importer.io.filler;

import java.io.File;

public class FileNameFromFileFiller extends AbstractFiller {

	public FileNameFromFileFiller(File file) {
		super(file);
	}

	public FileNameFromFileFiller(String path, String fileName) {
		super(path, fileName);
	}

	public FileNameFromFileFiller(String filePath) {
		super(filePath);
	}

	@Override
	public String getValue() {
		return file == null ? "" : file.getName();
	}
}
