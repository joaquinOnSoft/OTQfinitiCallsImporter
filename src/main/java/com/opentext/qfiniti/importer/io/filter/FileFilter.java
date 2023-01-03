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

public class FileFilter {
	/**
	 * Find files with a given extension in specified folder
	 * 
	 * @param dirName - folder name
	 * @param extension - file extension to be filtered
	 * @return list of files contained in a given folder that matches the given extension, e.g. .xls
	 */
	public File[] finder(String dirName, String... extensions) {
		String[] exts = extensions;
		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
		        for (String ext : exts) {
		             if (filename.endsWith(ext)) {
		                 return true;
		             }
		        }
		        
		        return false;
			}
		});

	}	
	
	/**
	 * Find files with a given extension in specified folder
	 * 
	 * @param dirName - folder name
	 * @param extension - file extension to be filtered
	 * @return list of files contained in a given folder that matches the given extension, e.g. .xls
	 */
	public File[] finder(String dirName, String extension) {
		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return filename.endsWith(extension);
			}
		});

	}
}
