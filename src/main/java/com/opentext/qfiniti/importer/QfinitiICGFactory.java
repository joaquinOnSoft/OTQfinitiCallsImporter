package com.opentext.qfiniti.importer;

public class QfinitiICGFactory {

	private static final String TYPE_NO_METADATA = "NoMetadata";
	private static final String TYPE_EXCEL = "xls";
	private static final String TYPE_CSV = "csv";

	public AbstractQfinitiICG getConfigGenerator(String type, String path) {
		AbstractQfinitiICG configGenerator = null;

		if (type != null) {
			if (type.equalsIgnoreCase(TYPE_NO_METADATA)) {
				configGenerator = new NoMetadataQfinitiICG(path);
			} else if (type.equalsIgnoreCase(TYPE_EXCEL)) {
				configGenerator = new XlsQfinitiICG(path);
			} else if (type.equalsIgnoreCase(TYPE_CSV)) {
				configGenerator = new CsvQfinitiICG(path);
			}
		}

		if (configGenerator == null) {
			throw new UnsupportedOperationException(
					"Qfiniti Importer Config Generator: '" + type + "' type not currently supported");
		}

		return configGenerator;
	}
}