package com.opentext.qfiniti.importer.io.transformer;

public interface ITransformer {
	static final String DATE_FORMAT_QFINITI = "dd/MM/yyyy HH:mm:ss";
	
	public String transform(String input);
}
