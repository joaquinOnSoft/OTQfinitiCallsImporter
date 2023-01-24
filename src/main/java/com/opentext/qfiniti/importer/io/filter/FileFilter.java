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
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileFilter {

	public File[] finder(boolean recursive, String dirName, String... extensions) {
		String[] exts = extensions;
		List<File> files = (List<File>) FileUtils.listFiles(new File(dirName), exts, recursive);

		File[] array = new File[files.size()];
		return files.toArray(array); 
	}		
	
	/**
	 * Find files with a given list of extensions in specified folder
	 * 
	 * @param dirName - folder name
	 * @param extension - file extension to be filtered
	 * @return list of files contained in a given folder that matches the given extension, e.g. .xls
	 */
	public File[] finder(String dirName, String... extensions) {
		return finder(false, dirName, extensions);
	}	
		
	/**
	 * Get all files with certain extensions in a directory 
	 * @param path
	 * @param fileExtension
	 * @return
	 * @throws IOException
	 * @see http://www.avajava.com/tutorials/lessons/how-do-i-get-all-files-with-certain-extensions-in-a-directory-including-subdirectories.html
	 */
    public File[]  finder(String path, String extension) {
    	String[] extensions = new String[] { extension};
		return finder(false, path, extensions);
    }
    
}
