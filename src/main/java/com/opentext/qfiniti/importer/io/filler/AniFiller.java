package com.opentext.qfiniti.importer.io.filler;

import java.io.File;

import com.opentext.qfiniti.importer.pojo.CallRecording;

public class AniFiller extends AbstractFiller {

	public AniFiller(CallRecording call, String path, String fileName) {
		super(call, path, fileName);
	}

	public AniFiller(CallRecording call, String filePath) {
		super(call, filePath);
	}

	public AniFiller(CallRecording call, File file) {
		super(call, file);
	}

	/**
	 * Generate a random ANI (Client phone number)
	 **/
	@Override
	public String getValue() {
		return String.format("6%08d", getRandom(1, 99999999));
	}
}
