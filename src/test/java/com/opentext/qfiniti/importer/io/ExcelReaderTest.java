package com.opentext.qfiniti.importer.io;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.opentext.qfiniti.importer.pojo.CallRecording;

public class ExcelReaderTest {

	public static String EXCEL_SAMPLE = "client-i/20191021.xls";
	private ExcelReader reader;

	@Before
	public void init() {
		reader = new ExcelReader();
	}

	@Test
	public void testRead() {
		// Read a file from resources folder
		// @see https://www.mkyong.com/java/java-read-a-file-from-resources-folder/
		String path = getClass().getClassLoader().getResource(EXCEL_SAMPLE).getFile();

		List<CallRecording> recordings = reader.read(path);

		assertNotNull(recordings);	
		assertEquals(50, recordings.size());
		
		CallRecording call = recordings.get(0);
		assertEquals("609974532", call.getAni());
		assertEquals("321014", call.getDnis());
		assertEquals("IRCall_2001788616D0191021.wav", call.getFileName());
		assertEquals(132, call.getDuration());
		//10/21/2019 11:59:20 PM
		assertEquals("21/10/2019 23:59:20", call.getDateTimeAsString());
		assertEquals("6BC8CF15-C7DD-D0F7-82E3-3418CC070001", call.getExtendedField("Recording ID"));
		assertEquals("Lineas SBC", call.getExtendedField("Initiation Policy"));
	}

}
