package com.opentext.qfiniti.importer.io.filler;

import java.io.File;

import org.junit.Before;

public class AbstractFillerTest {
	protected File file = null;

	@Before
	public void runBeforeTestMethod() {
		ClassLoader classLoader = getClass().getClassLoader();
		file = new File(classLoader.getResource("client-o/file_example_WAV_1MG.wav").getFile());
	}
}
