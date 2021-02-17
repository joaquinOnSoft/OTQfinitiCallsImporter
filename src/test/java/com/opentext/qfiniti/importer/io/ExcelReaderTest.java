package com.opentext.qfiniti.importer.io;

public class ExcelReaderTest extends AbstractReaderTest {

	@Override
	public void initialize() {
		configFilePath = "client-i/client-i-mapping.json";
		dataFilePath = "client-i/20191021.xls";
		reader = new ExcelReader();
	}
}
