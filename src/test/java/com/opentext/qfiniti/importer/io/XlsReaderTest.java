package com.opentext.qfiniti.importer.io;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.opentext.qfiniti.importer.JSonConfigReader;
import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.pojo.MappingConfig;

public class XlsReaderTest {
	@Test
	public void read() {
		ClassLoader classLoader = getClass().getClassLoader();
		
		File configFile = new File(classLoader.getResource("client-i/client-i-mapping.json").getFile());
		JSonConfigReader configReader = new JSonConfigReader();
		MappingConfig config = configReader.read(configFile);
		
		File excelFile = new File(classLoader.getResource("client-i/20191021.xls").getFile());
				
		
		XlsReader reader = new XlsReader();
		List<CallRecording> callRecordings = reader.read(excelFile.getAbsolutePath(), config);
		assertNotNull(callRecordings);
		assertTrue(callRecordings.size() > 0);
		for(CallRecording recording: callRecordings) {
			System.out.println(recording);
		}
	}
}
