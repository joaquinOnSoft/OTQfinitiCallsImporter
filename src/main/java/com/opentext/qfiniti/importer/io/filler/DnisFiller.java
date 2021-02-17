package com.opentext.qfiniti.importer.io.filler;

import java.io.File;

public class DnisFiller extends AbstractFiller {

	public DnisFiller(String path, String fileName) {
		super(path, fileName);
	}

	public DnisFiller(String filePath) {
		super(filePath);
	}

	public DnisFiller(File file) {
		super(file);
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
