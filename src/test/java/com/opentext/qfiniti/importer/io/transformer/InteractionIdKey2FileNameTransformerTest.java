package com.opentext.qfiniti.importer.io.transformer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class InteractionIdKey2FileNameTransformerTest {

	@Test
	public void transform() {
		InteractionIdKey2FileNameTransformer transformer = new InteractionIdKey2FileNameTransformer();
		String fileName = transformer.transform("2001788444D0191021");
		assertNotNull(fileName);
		assertEquals("IRCall_2001788444D0191021.wav", fileName);
	}
}
