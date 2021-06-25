package com.opentext.qfiniti.importer.io.filler;

import java.io.File;
import java.time.LocalDateTime;

import org.junit.Before;

import com.opentext.qfiniti.importer.pojo.CallRecording;

public class AbstractFillerTest {
	protected File file = null;
	protected CallRecording call = null;

	protected String getFilePath() {
		return "client-o/file_example_WAV_1MG.wav";
	}

	@Before
	public void runBeforeTestMethod() {
		ClassLoader classLoader = getClass().getClassLoader();
		file = new File(classLoader.getResource(getFilePath()).getFile());
		
		//Placeholder values
		call = new CallRecording(System.getProperty("user.dir"), "audio.wav", 249, LocalDateTime.now());
		call.addExtendedField("SG", "VFS_TI_MV_RIC");
		call.addExtendedField("Team", "VS-TI-FL-Team36");
	}
}
