package com.opentext.qfiniti.importer.io.filler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class FileNameFromFileFillerTest extends AbstractFillerTest {
	@Test
	public void testGetValue() {
		FileNameFromFileFiller filler = new FileNameFromFileFiller(call, file);
		String value = filler.getValue();

		assertNotNull(value);
		assertEquals("file_example_WAV_1MG.wav", value);
	}
}
