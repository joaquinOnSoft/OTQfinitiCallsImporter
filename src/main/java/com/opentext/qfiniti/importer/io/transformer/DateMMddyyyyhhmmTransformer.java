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
 *     Joaqu�n Garz�n - initial implementation
 *
 */
package com.opentext.qfiniti.importer.io.transformer;

public class DateMMddyyyyhhmmTransformer extends AbstractDateTransformer {

	// Date format example: 04/05/2021 21:54
	private static final String DATE_FORMAT_MM_DD_YYYY_HH_MM = "MM/dd/uuuu HH:mm";

	public DateMMddyyyyhhmmTransformer(String path) {
		super(path);
	}

	/**
	 * Transforms a date from 'MM/dd/yyyy hh:mm' to format 'dd/MM/yyyy HH:mm:ss'
	 * 
	 * @param strDate - date expressed in format "MM/dd/yyyy hh:mm"
	 * @return date in format 'dd/MM/yyyy HH:mm:ss'
	 * @see https://www.programmersought.com/article/83121948467/
	 */
	@Override
	public String transform(String strDate) {
		return transformDateWithOptionalTime(strDate, DATE_FORMAT_MM_DD_YYYY_HH_MM);
	}
}
