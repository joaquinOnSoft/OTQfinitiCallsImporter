package com.opentext.qfiniti.importer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.opentext.qfiniti.importer.pojo.FieldMapping;
import com.opentext.qfiniti.importer.pojo.MappingConfig;

public class JSonConfigReaderTest {

	@Test
	public void readFromFile() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("client-i/client-i-mapping.json").getFile());

		JSonConfigReader configReader = new JSonConfigReader();
		MappingConfig config = configReader.read(file);
		assertNotNull(config);
		assertEquals("xls", config.getInputType());

		List<FieldMapping> fielMapping = config.getFieldMapping();
		assertNotNull(fielMapping);
		assertTrue(fielMapping.size() > 0);
		assertEquals("Media Type", fielMapping.get(0).getIname());
		assertEquals("String", fielMapping.get(0).getItype());
	}
}
