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
package com.opentext.qfiniti.importer.io.transformer;

public class DateMMddyyyyhhmmssaTransformer extends AbstractDateTransformer  {

	private static final String DATE_FORMAT_MM_DD_YYYY_HH_MM_SS_A = "MM/dd/yyyy hh:mm:ss a";
			
	public DateMMddyyyyhhmmssaTransformer(String path) {
		super(path);
	}
	
	/**
	 * Transforms a date from 'MM/dd/yyyy hh:mm:ss a' to format 'dd/MM/yyyy HH:mm:ss'
	 * 
	 * @param strDate - date expressed in format 'MM/dd/yyyy hh:mm:ss a'
	 * @return date in format 'dd/MM/yyyy HH:mm:ss'
	 */
	@Override
	public String transform(String strDate) {
		return transform(strDate, DATE_FORMAT_MM_DD_YYYY_HH_MM_SS_A);		
	}
	
}
