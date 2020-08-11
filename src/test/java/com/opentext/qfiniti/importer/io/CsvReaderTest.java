package com.opentext.qfiniti.importer.io;


public class CsvReaderTest extends AbstractReaderTest{

	@Override
	public void initialize() {
		configFilePath = "client-i/client-i-mapping.json";
		dataFilePath = "client-i/20191021.csv";
		reader = new CsvReader();
	}
}
