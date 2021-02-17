package com.opentext.qfiniti.importer.io.filler;

import java.io.File;

import org.junit.Before;

public class AbstractFillerTest {
	protected File file = null;

	protected String getFilePath() {
		return "client-o/file_example_WAV_1MG.wav";
	}
	
	@Before
	public void runBeforeTestMethod() {
		ClassLoader classLoader = getClass().getClassLoader();
		file = new File(classLoader.getResource(getFilePath()).getFile());
	}
}
