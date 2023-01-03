package com.opentext.qfiniti.importer.io.filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;


public abstract class FileFilterTest {

	protected String dataFileFullPath;
	protected String dataFileName;

	protected abstract File[] applyFilter(String path);
	
	@Test
	public void testFinder() {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(dataFileFullPath).getFile());

		assertNotNull(file);

		String path = file.getParentFile().getAbsolutePath();

		assertNotNull(path);
		
		File[] files = applyFilter(path);

		assertNotNull(files);
		assertTrue(files.length > 0);
		assertEquals(dataFileName, files[0].getName());
	}
}
