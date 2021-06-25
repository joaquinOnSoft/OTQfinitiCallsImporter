package com.opentext.qfiniti.importer.io.filler;

import java.io.File;

import com.opentext.qfiniti.importer.pojo.CallRecording;

public class FileNameFromFileFiller extends AbstractFiller {

	public FileNameFromFileFiller(CallRecording call, File file) {
		super(call, file);
	}

	public FileNameFromFileFiller(CallRecording call, String path, String fileName) {
		super(call, path, fileName);
	}

	public FileNameFromFileFiller(CallRecording call, String filePath) {
		super(call, filePath);
	}

	@Override
	public String getValue() {
		return file == null ? "" : file.getName();
	}
}
