package com.opentext.qfiniti.importer.io.filler;

import java.io.File;

import com.opentext.qfiniti.importer.pojo.CallRecording;

public class DnisFiller extends AbstractFiller {

	public DnisFiller(CallRecording call, String path, String fileName) {
		super(call, path, fileName);
	}

	public DnisFiller(CallRecording call, String filePath) {
		super(call, filePath);
	}

	public DnisFiller(CallRecording call, File file) {
		super(call, file);
	}

	/**
	 * Generate a random DNIS (Call type)
	 */
	@Override
	public String getValue() {
		int dnis[] = { 1000, 2000, 3000, 4000, 5000 };
		int index = getRandom(0, 4);

		return String.format("%04d", dnis[index]);
	}
}
