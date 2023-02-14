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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;

import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

/**
 * The Apache Tika™ toolkit detects and extracts metadata and text from over a
 * thousand different file types (such as PPT, XLS, and PDF).
 * 
 * @author Joaquín Garzón
 */
public class TikaMetadataExtractor extends AbstractAudioMetadataExtractor {

	private static final Logger log = LogManager.getLogger(TikaMetadataExtractor.class);
	
	/**
	 * Detects and extracts metadata from over a thousand different file types (such
	 * as PPT, XLS, and PDF).
	 * 
	 * @param f - File to be processed
	 * @return Metadata extracted from the file. Example of metadata extracted:
	 * 
	 *         xmpDM: audioSampleRate = 8000,
	 *         channels = 2,
	 *         X - TIKA: Parsed - By = org.apache.tika.parser.DefaultParser,
	 *         X - TIKA: Parsed - By - Full - Set = org.apache.tika.parser.DefaultParser,
	 *         bits = 16,
	 *         encoding = PCM_SIGNED,
	 *         xmpDM: audioSampleType = 16Int,
	 *         Content - Type = audio / vnd.wave,
	 *         samplerate = 8000.0
	 * 
	 * @throws IOException
	 */
	public Map<String, String> extract(File f) throws IOException {
		Map<String, String> output = null;

		if (f != null) {			
			// SEE: Tika Auto Detector Parser
			// https://www.javatpoint.com/tika-auto-detector-parser
	        BodyContentHandler handler = new BodyContentHandler();  
	        
	        AutoDetectParser parser = new AutoDetectParser();  
	        Metadata metadata = new Metadata();  
	        try (InputStream stream = new FileInputStream(f) ) {  
	            parser.parse(stream, handler, metadata);  
	        }          
	        catch (SAXException e) {
				log.error("Tika auto detector parser (SAX): ", e);
			} catch (TikaException e) {
				log.error("Tika auto detector parser: ", e);
			}
	        			
			if (metadata != null) {
				String[] names = metadata.names();
				int size = names.length;
				output = new HashMap<String, String>();

				for (int i = 0; i < size; i++) {
					output.put(names[i], metadata.get(names[i]));
				}
				
				//Calculate file duration in seconds
				float duration = getDuration(f.length(),
						metadata.get(SAMPLE_RATE), 
						metadata.get(CHANNELS),
						metadata.get(BITS));
				output.put(DURATION, Integer.toString((int) duration));
			}
		}

		return output;
	}
}
