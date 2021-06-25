package com.opentext.qfiniti.importer.util;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Test;

public class FilesInFolderCacheTest {
	
	@Test
	public void getFileFromPrefix() {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("client-o").getFile());
		
		String fileName = FilesInFolderCache.getInstance().getFileFromPrefix(file.toPath(), "0001");
		assertEquals("0001.wav", fileName);
		
		fileName = FilesInFolderCache.getInstance().getFileFromPrefix(file.toPath(), "0002");
		assertEquals("0002.wav", fileName);		
	}
}
