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
package com.opentext.qfiniti.importer.io.filler;

import java.io.File;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;

import com.opentext.qfiniti.importer.pojo.CallRecording;

public class AbstractFillerTest {
	protected File file = null;
	protected CallRecording call = null;

	protected String getFilePath() {
		return "client-o/file_example_WAV_1MG.wav";
	}

	@BeforeEach
	public void runBeforeTestMethod() {
		ClassLoader classLoader = getClass().getClassLoader();
		file = new File(classLoader.getResource(getFilePath()).getFile());
		
		//Placeholder values
		call = new CallRecording(System.getProperty("user.dir"), "audio.wav", 249, LocalDateTime.now());
		call.addExtendedField("SG", "VFS_TI_MV_RIC");
		call.addExtendedField("Team", "VS-TI-FL-Team36");
	}
}
