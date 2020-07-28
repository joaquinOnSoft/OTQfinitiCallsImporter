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
package com.opentext.qfiniti.importer.io.filter;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Find folders in specified folder [closed]
 * SEE: https://stackoverflow.com/questions/1384947/java-find-txt-files-in-specified-folder/1385015
 * */
public class FolderFilter {

	/**
	 * Find folders in specified folder [closed]
	 * @param dirName - folder name
	 * @return list of folders contained in a given folder
	 */
	public File[] finder( String dirName){
		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() { 
			public boolean accept(File dir, String filename) {
				return dir.isDirectory(); 
			}
		} );

	}
}
