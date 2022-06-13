package com.opentext.qfiniti.importer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.opentext.qfiniti.importer.configgen.AbstractQfinitiICG;
import com.opentext.qfiniti.importer.configgen.CsvQfinitiICG;
import com.opentext.qfiniti.importer.configgen.NoMetadataQfinitiICG;
import com.opentext.qfiniti.importer.configgen.XlsQfinitiICG;

public class QfinitiICGFactoryTest {
	private QfinitiICGFactory factory = new QfinitiICGFactory();
	private AbstractQfinitiICG config = null;

	@Test
	public void getConfigGeneratorXls() {
		config = factory.getConfigGenerator("xls", null);
		assertTrue(config instanceof XlsQfinitiICG);
	}

	@Test
	public void getConfigGeneratorNoMetadata() {
		config = factory.getConfigGenerator("NoMetadata", null);
		assertTrue(config instanceof NoMetadataQfinitiICG);
	}

	@Test
	public void getConfigGeneratorCsv() {
		config = factory.getConfigGenerator("csv", null);
		assertTrue(config instanceof CsvQfinitiICG);
	}

	@Test
	public void getConfigGeneratorInvalidType() {
		try {
			config = factory.getConfigGenerator("asdfg", null);
		} catch (UnsupportedOperationException e) {
			String msg = e.getMessage();
			assertEquals("Qfiniti Importer Config Generator: 'asdfg' type not currently supported", msg);
		}
	}
}
