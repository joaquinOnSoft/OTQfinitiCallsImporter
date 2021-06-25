/*
 *   (C) Copyright 2019 OpenText and others.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *   Contributors:
 *     Joaquín Garzón - initial implementation
 *
 */
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