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

/**
 * Transform a user id to a user surname.
 */
public class UserId2UserSurnameTransformer extends UserId2UserNamesTransformer {
	
	public UserId2UserSurnameTransformer(String path) {
		super(path);
	}

	/**
	 * Transform a user id to a user surname.
	 * <strong>NOTE:</strong> Requires a .properties file called 'user-mapping.properties'
	 * This file looks like this:
	 * <pre>
	 *    93754d84-c59e-417d-ae00-3c62a424fae3=Natasha Romanoff
	 *    35424d84-c59e-417d-be63-3c62a458cad3=Bruce Banner
	 *    25854d84-c59e-417d-ae00-3c62a424bea0=Peter Parker
	 * </pre>
	 */
	@Override
	public String transform(String userId) {
		String name = "";
		
		if(userId != null) {
			String[] names = getNamesFromUserId(userId);
			if (names != null && names.length == 2) {
				name = names[INDEX_SURNAME];
			}
		}
		
		return name;
	}

}
