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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import wave.WavHeader;
import wave.WavHeaderReader;

public class JavaMetadataExtractor implements IMetadataCreator {

	/**
	 * Reading wave format (.wav) header
	 * @see https://github.com/tkaczenko/WavReader 
	 */
	@Override
	public Map<String, String> extract(File audio) throws IOException {
		HashMap<String, String> metadata = new HashMap<String, String>();

		WavHeader wavHeader = null;
        try {
            WavHeaderReader wavHeaderReader = new WavHeaderReader(audio.getAbsolutePath());
            wavHeader = wavHeaderReader.read();
            log.debug(wavHeader.toString());          
        } catch (FileNotFoundException e) {
            log.error("Error: File " + audio.getAbsolutePath() + " not found!");
        } catch (IOException e) {
        	log.error("Error: " + e.getMessage());
        }

        if(wavHeader != null) {
			//time = FileLength / (Sample Rate * Channels * Bits per sample /8)
			double duration = 0;
			if (wavHeader.getSampleRate() != 0 && wavHeader.getBitsPerSample() != 0 &&  wavHeader.getNumChannels() != 0) {
				duration  = wavHeader.getChunkSize() / (wavHeader.getSampleRate() * wavHeader.getBitsPerSample() * wavHeader.getNumChannels() / 8);
			}
			metadata.put(DURATION, Integer.toString((int) duration)); // In seconds
			metadata.put(SAMPLE_RATE, Integer.toString(wavHeader.getSampleRate()));
			metadata.put(BIT_RATE, Integer.toString(wavHeader.getByteRate() * 8));
			metadata.put(BITS, Integer.toString(wavHeader.getBitsPerSample()));
			metadata.put(CHANNELS, Integer.toString(wavHeader.getNumChannels()));
        }
        else {
        	log.warn("Audio header file not present or invalid");
        }
        
		return metadata;
	}

}
