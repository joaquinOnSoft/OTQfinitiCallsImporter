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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.opentext.qfiniti.importer.util.FilesInFolderCache;

public class ExtensionPrefix2FileNameCachedTransformer extends AbstractTransformer {
	
	public ExtensionPrefix2FileNameCachedTransformer(String path) {
		super(path);
	}

	/**
	 * Find a call recording file name from a prefix, e.g. from and prefix like
	 * <strong>ext2960006643_05_04_2021_12;40;57</strong> will search a file in the 
	 * provided path that match the given prefix:
	 * 
	 * ext2960006643_05_04_2021_12;40;57_18638_it1483yw.wav
	 * 
	 * @param prefix - Call recording file name prefix
	 * @return call recording file name
	 * 
	 * @see https://mkyong.com/java/java-files-walk-examples/#find-files-by-filename
	 */
	@Override
	public String transform(String prefix) {			
		String fileName = null;
		
		fileName = FilesInFolderCache.getInstance().getFileFromPrefix(Path.of(path), prefix);
		
		log.debug("transform(" + prefix +") :" + fileName);
		
		return fileName;
	}

}
