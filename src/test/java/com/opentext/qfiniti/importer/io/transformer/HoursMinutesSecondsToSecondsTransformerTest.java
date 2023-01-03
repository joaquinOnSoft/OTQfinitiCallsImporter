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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HoursMinutesSecondsToSecondsTransformerTest extends AbstractTransformerTest{

	@Test
	public void transform() {
		HoursMinutesSecondsToSecondsTransformer adapter = new HoursMinutesSecondsToSecondsTransformer(path);

		String seconds = adapter.transform("00:10:10");
		assertEquals("610", seconds);

		seconds = adapter.transform("10:00:10");
		assertEquals("36010", seconds);
	}
}
