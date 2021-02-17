package com.opentext.qfiniti.importer.io.filter;

import java.io.File;
import java.io.FilenameFilter;

public class FileFilter {
	/**
	 * Find files with a given extension in specified folder
	 * 
	 * @param dirName - folder name
	 * @return list of .xls files contained in a given folder
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
