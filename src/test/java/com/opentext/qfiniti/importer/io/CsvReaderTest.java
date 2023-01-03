package com.opentext.qfiniti.importer.io;

import org.junit.jupiter.api.BeforeEach;

public class CsvReaderTest extends AbstractReaderTest {

	@Override
	@BeforeEach
	public void initialize() {
		configFilePath = "client-i/client-i-mapping.json";
		dataFilePath = "client-i/20191021.csv";
		reader = new CsvReader();
	}
}
