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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.wav.WavTag;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagException;

/**
 * Jaudiotagger is the Audio Tagging library used for tagging data in Audio files. 
 * It currently fully supports Mp3, Mp4 (Mp4 audio, M4a and M4p audio) Ogg Vorbis, 
 * Flac and Wma, there is limited support for Wav and Real formats.
 * 
 * SEE: https://bitbucket.org/ijabz/jaudiotagger/src/master/
 * SEE: https://bitbucket.org/ijabz/jaudiotagger/src/65dcd55dfedee64a93890f114ddba9e025ec2095/srctest/org/jaudiotagger/tag/wav/WavMetadataTest.java#WavMetadataTest.java-35
 * 
 * @author Joaquín Garzón
 */
public class JaudiotaggerMetadataExtractor implements IMetadataCreator {

	private static final Logger log = LogManager.getLogger(JaudiotaggerMetadataExtractor.class);
	
	/**
	 * 
	 * @param audio
	 * @return
	 * Example of metadata provided:
	 *    Encoding infos content:
	 *      SAMPLING : 8000
	 *      LENGTH : 33.536938
	 *      VBR : false
	 *      CHANNB : 2
	 *      INFOS : 
	 *      TYPE : WAV-RIFF 16 bits
	 *      BITRATE : 256
	 * @throws IOException
	 */
	@Override
	public Map <String, String> extract(File audio) throws IOException{
		AudioFile f;
		try {
			f = AudioFileIO.read(audio);
		} catch (CannotReadException | TagException | ReadOnlyFileException | InvalidAudioFrameException e) {
			log.error(e.getMessage());
			throw new IOException(e.getMessage());
		}

		WavTag tag = (WavTag) f.getTag();

		HashMap<String, String> metadata = new HashMap<String, String>();

		metadata.put(TITLE, tag.getFirst(FieldKey.TITLE));
		metadata.put(ARTIST, tag.getFirst(FieldKey.ARTIST));
		metadata.put(DURATION, Integer.toString( f.getAudioHeader().getTrackLength() ) ); // In seconds
		metadata.put(SAMPLE_RATE, f.getAudioHeader().getSampleRate() ); 
		metadata.put(BITS, f.getAudioHeader().getBitRate() ); 
		metadata.put(CHANNELS, f.getAudioHeader().getChannels() ); 

		//System.out.println(metadata);
		
		return metadata;		       
	}
}
