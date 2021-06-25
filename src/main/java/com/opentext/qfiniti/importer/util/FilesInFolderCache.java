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
package com.opentext.qfiniti.importer.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FilesInFolderCache {

	protected static final Logger log = LogManager.getLogger(FilesInFolderCache.class);

	private static FilesInFolderCache instance;
	private Map<Path, List<Path>> cache;

	private FilesInFolderCache(){
		cache = new HashMap<Path, List<Path>>();
	}

	public static FilesInFolderCache getInstance(){
		if(instance == null){
			instance = new FilesInFolderCache();
		}
		return instance;
	}

	public String getFileFromPrefix(Path path, String prefix) {
		String fileName = null;

		if(!cache.containsKey(path)) {
			// Uses Files.walk to list all files from a directory, including 
			// all levels of sub-directories (default).
			// SEE: https://mkyong.com/java/java-files-walk-examples/#list-all-files
			List<Path> fileList = null;
			try (Stream<Path> walk = Files.walk(path)) {
				fileList = walk.filter(Files::isRegularFile)
						.collect(Collectors.toList());
			} catch (IOException e) {
				log.info("Path: " + path.toString());
				log.error("Error trying to retrieve files in folder (and subfolders): ", e);
			}

			if (fileList != null) {
				cache.put(path, fileList);
			}    
		}
		
		if(cache.containsKey(path)){
			List<Path> fileList = cache.get(path);

			log.info(fileList.get(0).getFileName().toString());
			
			List<Path> filesFound = fileList.stream()
					.filter(p -> p.getFileName().toString().startsWith(prefix) == true)
					.collect(Collectors.toList());

			if(filesFound != null && filesFound.size() > 0) {
				fileName = filesFound.get(0).getFileName().toString();
			}
		}
		else {
		}

		return fileName != null ? fileName.toString() : null;
	}
}
