package com.opentext.qfiniti.importer;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.jupiter.api.Test;

import com.opentext.qfiniti.importer.configgen.AbstractQfinitiICG;
import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.pojo.MappingConfig;

public abstract class AbstractQfinitiICGTest {
	protected String folderPath;
	protected String jsonConfigPath;
	protected String outputFileName;

	public abstract AbstractQfinitiICG getQfinitiICG(String path);

	@Test
	public void testGenerate() {
		List<CallRecording> recordings = null;

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(folderPath).getFile());
		String path = file.getAbsolutePath();

		File jsonFile = new File(classLoader.getResource(jsonConfigPath).getFile());
		JSonConfigReader jsonConfigReader = new JSonConfigReader();
		MappingConfig mapping = jsonConfigReader.read(jsonFile);

		AbstractQfinitiICG configGenerator = getQfinitiICG(path);
		configGenerator.setOutput(outputFileName);
		configGenerator.setMappingConfig(mapping);
		try {
			recordings = configGenerator.generate();
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (InvalidFormatException e) {
			fail(e.getMessage());
		}

		assertNotNull(recordings);
	}

}
