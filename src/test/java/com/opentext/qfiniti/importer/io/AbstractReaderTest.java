package com.opentext.qfiniti.importer.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.opentext.qfiniti.importer.JSonConfigReader;
import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.pojo.MappingConfig;

public abstract class AbstractReaderTest {
	protected String configFilePath = null;
	protected String dataFilePath = null;
	protected IReader reader;

	@BeforeEach
	public abstract void initialize();

	@Test
	public void read() {
		ClassLoader classLoader = getClass().getClassLoader();

		File configFile = new File(classLoader.getResource(configFilePath).getFile());
		JSonConfigReader configReader = new JSonConfigReader();
		MappingConfig config = configReader.read(configFile);

		File dataFile = new File(classLoader.getResource(dataFilePath).getFile());

		List<CallRecording> callRecordings = reader.read(dataFile.getAbsolutePath(), config);
		assertNotNull(callRecordings);
		assertEquals(50, callRecordings.size());

		CallRecording call0 = callRecordings.get(0);
		assertEquals(
				"C:\\Users\\jgarzonpena\\eclipse-workspace\\OTQfinitiCallsImporter\\src\\test\\resources\\client-i\\samples",
				call0.getPathName());
		assertEquals("IRCall_2001788616D0191021.wav", call0.getFileName());
		assertEquals("21/10/2019 23:59:20", call0.getDateTimeAsString());
		assertEquals(132, call0.getDuration());
		assertEquals("MARTINEZ, IVAN", call0.getTeamMemberName());
		assertEquals("Client-i", call0.getGroupHierachy());
		assertEquals("609974532", call0.getAni());
		assertEquals("321014", call0.getDnis());

		CallRecording call49 = callRecordings.get(49);
		assertEquals(
				"C:\\Users\\jgarzonpena\\eclipse-workspace\\OTQfinitiCallsImporter\\src\\test\\resources\\client-i\\samples",
				call0.getPathName());
		assertEquals("IRCall_2001788430D0191021.wav", call49.getFileName());
		assertEquals("21/10/2019 22:58:09", call49.getDateTimeAsString());
		assertEquals(234, call49.getDuration());
		assertEquals("MARTIN MARTIN, PATRICIA", call49.getTeamMemberName());
		assertEquals("Client-i", call49.getGroupHierachy());
		assertEquals("679445130", call49.getAni());
		assertEquals("321014", call49.getDnis());
	}

}
