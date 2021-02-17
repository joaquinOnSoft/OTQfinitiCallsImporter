package com.opentext.qfiniti.importer.io.filler;

import java.io.File;

public abstract class AbstractFiller {
	protected File file;

	public AbstractFiller(String path, String fileName) {
		this(path + File.separator + fileName);
	}

	public AbstractFiller(String filePath) {
		this(new File(filePath));
	}

	public AbstractFiller(File file) {
		this.file = file;
	}

	public abstract String getValue();

	protected int getRandom(int min, int max) {
		return (int) (Math.random() * ((max - min) + 1)) + min;
	}
}
