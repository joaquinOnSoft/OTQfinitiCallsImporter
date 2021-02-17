package com.opentext.qfiniti.importer.io.filler;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class FileNameFromFileFillerTest extends AbstractFillerTest {
	@Test
	public void testGetValue() {
		FileNameFromFileFiller filler = new FileNameFromFileFiller(file);
		String value = filler.getValue();
		
		assertNotNull(value);
		assertEquals("file_example_WAV_1MG.wav", value);
	}}
