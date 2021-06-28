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

/**
 * Direction of the call. Must be a number 0-2. Valid values: 
 * <ul>
 *    <li>0 – Unknown</li>
 *    <li>1 – Inbound</li>
 *    <li>2 – Outbound</li>
 * </ul>   
 *
 */
public class CallDirectionTransformer extends AbstractTransformer {
	// Direction of the call: 0 – Unknown
	private static final int CD_CODE_UNKNOWN = 0;
	// Direction of the call: 1 – Inbound	
	private static final int CD_CODE_INBOUND = 1;
	// Direction of the call:2 – Outbound	
	private static final int CD_CODE_OUTBOUND = 2;

	// Direction of the call: 1 – Inbound		
	private static final String CALL_DIRECTION_INBOUND = "INBOUND";
	// Direction of the call:2 – Outbound		
	private static final String CALL_DIRECTION_OUTBOUND = "OUTBOUND";
	
	public CallDirectionTransformer(String path) {
		super(path);
	}

	/**
	 * Transform a text literal ('Inbound' or 'Outbound') into 
	 * a integer to indicate the 'call direction':
	 * <ul>
	 *    <li>0 – Unknown</li>
	 *    <li>1 – Inbound</li>
	 *    <li>2 – Outbound</li>
	 * </ul>    
	 */
	@Override
	public String transform(String callDirection) {			
		int iCallDirection = CD_CODE_UNKNOWN;
		
		if(callDirection != null) {
			switch (callDirection.toUpperCase()) {
			case CALL_DIRECTION_INBOUND:
				iCallDirection = CD_CODE_INBOUND;
				break;
			case CALL_DIRECTION_OUTBOUND:
				iCallDirection = CD_CODE_OUTBOUND;
			}
		}
		
		return Integer.toString(iCallDirection);
	}

}
