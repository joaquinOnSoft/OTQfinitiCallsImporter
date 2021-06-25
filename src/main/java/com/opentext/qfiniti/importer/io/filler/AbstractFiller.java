package com.opentext.qfiniti.importer.io.filler;

import java.io.File;

import com.opentext.qfiniti.importer.pojo.CallRecording;

public abstract class AbstractFiller {
	protected File file;
	protected CallRecording call;

	public AbstractFiller(CallRecording call, String path, String fileName) {
		this(call, path + File.separator + fileName);
	}

	public AbstractFiller(CallRecording call, String filePath) {
		this(call, new File(filePath));
	}

	public AbstractFiller(CallRecording call, File file) {
		this.call = call;
		this.file = file;
	}

	public abstract String getValue();

	protected int getRandom(int min, int max) {
		return (int) (Math.random() * ((max - min) + 1)) + min;
	}
}
