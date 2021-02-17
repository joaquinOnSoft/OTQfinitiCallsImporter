package com.opentext.qfiniti.importer.io;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import com.opentext.qfiniti.importer.JSonConfigReader;
import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.pojo.MappingConfig;

public class ExcelWriterTest {
	@Test
	public void write() {
		String outputFileName = "excel_writer_test.xls";

		ClassLoader classLoader = getClass().getClassLoader();
		File configFile = new File(classLoader.getResource("client-i/client-i-mapping.json").getFile());
		JSonConfigReader configReader = new JSonConfigReader();
		MappingConfig config = configReader.read(configFile);

		CallRecording call = new CallRecording();
		call.setPathName(
				"C:\\Users\\jgarzonpena\\eclipse-workspace\\OTQfinitiCallsImporter\\src\\test\\resources\\client-i\\samples");
		call.setFileName("IRCall_2001788616D0191021.wav");
		call.setDateTime("31/07/2020 16:35:12");
		call.setDuration(234);
		call.setTeamMemberName("Parker, Peter");
		call.setGroupHierachy("Marbel");
		call.setAni("666999888");
		call.setDnis("1234");

		List<CallRecording> callRecordings = new LinkedList<CallRecording>();
		callRecordings.add(call);

		ExcelWriter writer = new ExcelWriter();
		try {
			writer.write(config.getColumnNames(), callRecordings, outputFileName);
		} catch (InvalidFormatException | IOException e) {
			fail("Excel writer not working");
		}

		File output = new File(outputFileName);
		assertTrue(output.exists());
	}
}
