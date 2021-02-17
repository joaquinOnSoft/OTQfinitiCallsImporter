package com.opentext.qfiniti.importer.io.filler;

import java.io.File;

public class AniFiller extends AbstractFiller {

	public AniFiller(String path, String fileName) {
		super(path, fileName);
	}

	public AniFiller(String filePath) {
		super(filePath);
	}

	public AniFiller(File file) {
		super(file);
	}

	/**
	 * Generate a random ANI (Client phone number)
	 **/
	@Override
	public String getValue() {
		return String.format("6%08d", getRandom(1, 99999999));
	}
}
