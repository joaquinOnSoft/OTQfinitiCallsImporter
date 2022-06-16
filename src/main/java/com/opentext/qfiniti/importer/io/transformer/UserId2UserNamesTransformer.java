/*
 *   (C) Copyright 2022 OpenText and others.
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

import com.opentext.qfiniti.importer.util.PorpertiesCache;

/**
 * Transform a user id to a user name.
 */
public abstract class UserId2UserNamesTransformer extends AbstractTransformer {
	
	protected static final int INDEX_NAME = 0;
	protected static final int INDEX_SURNAME = 1;
	
	public UserId2UserNamesTransformer(String path) {
		super(path);
	}

	
	protected String[] getNamesFromUserId(String userId) {		
		String fullName;
		String names[] = new String[2];
		names[INDEX_NAME] = userId;
		names[INDEX_SURNAME] = userId;
		
		if(userId != null) {
			PorpertiesCache instance = PorpertiesCache.getInstance("user-mapping.properties");
			if(instance != null) {
				fullName = instance.getProperty(userId);
				if(fullName != null) {
					int indexPoint = fullName.indexOf(".");
					if(indexPoint > 0) {
						names[INDEX_NAME] = fullName.substring(0, indexPoint).trim();
						if(indexPoint + 1 < fullName.length()) {
							names[INDEX_SURNAME] = fullName.substring(indexPoint + 1).trim();
						}
					}
					else {
						int indexSpace = fullName.indexOf(" ");
						if(indexSpace > 0) {
							names[INDEX_NAME] = fullName.substring(0, indexSpace).trim();
							if(indexSpace + 1 < fullName.length()) {
								names[INDEX_SURNAME] = fullName.substring(indexSpace + 1).trim();
							}
						}						
					}
				}
			}
			
		}
		
		return names;		
	}

}
