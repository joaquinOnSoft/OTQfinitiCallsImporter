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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Convert call duration from milliseconds to seconds
 */
public class DurationMsecToSecTransformer extends AbstractTransformer {
	
	private static final double MILLISECONDS_IN_A_SECOND = 1000;
	private static final Logger log = LogManager.getLogger(DurationMsecToSecTransformer.class);
	
	
	public DurationMsecToSecTransformer(String path) {
		super(path);
	}

	/**
	 * Convert call duration from milliseconds to seconds   
	 */
	@Override
	public String transform(String durationInMSec) {			
		double duration = 0;
		
		try {
			duration = Integer.parseInt(durationInMSec) / MILLISECONDS_IN_A_SECOND;
		}
		catch (NumberFormatException e) {
			log.debug("Invalid duration time in milliseconds: " + durationInMSec);
		}
		
		return Double.toString(duration);
	}
}
