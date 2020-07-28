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
package com.opentext.qfiniti.importer.io.metadata;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.tika.Tika;
import org.apache.tika.metadata.Metadata;

/**
 * The Apache Tika™ toolkit detects and extracts metadata and text 
 * from over a thousand different file types (such as PPT, XLS, and PDF). 
 * @author Joaquín Garzón
 */
public class TikaMetadataExtractor implements IMetadataCreator{

	/**
	 * Detects and extracts metadata from over a 
	 * thousand different file types (such as PPT, XLS, and PDF). 
	 * @param f - File to be processed
	 * @return Metadata extracted from the file. 
	 * Example of metadata extracted:
	 * 
	 * 		X-Parsed-By=org.apache.tika.parser.DefaultParser
	 * 		X-Parsed-By=org.apache.tika.parser.audio.AudioParser
	 * 		xmpDM:audioSampleRate=8000
	 * 		channels=2
	 * 		bits=16
	 * 		resourceName=file_example_WAV_1MG.wav
	 * 		Content-Length=1073218
	 * 		encoding=PCM_SIGNED
	 * 		xmpDM:audioSampleType=16Int
	 * 		Content-Type=audio/vnd.wave
	 * 		samplerate=8000.0
	 * 
	 * @throws IOException
	 */
	public Map<String, String> extract(File f) throws IOException{
		Map<String, String> output = null;
		
		if (f != null) {
			Tika tika = new Tika();
			Metadata metadata = new Metadata();
			
			try {
				tika.parse(f, metadata);
			} catch (IOException e) {
				System.err.println(e.getMessage());
				throw e;
			}
			
			//System.out.println(metadata);
			if(metadata != null) {
				String [] names = metadata.names();
				int size = names.length;
				output = new HashMap<String, String>();
				
				for(int i=0; i<size; i++) {
					output.put(names[i], metadata.get(names[i]));
				}
			}
		}	
		
		return output;
	}
}
