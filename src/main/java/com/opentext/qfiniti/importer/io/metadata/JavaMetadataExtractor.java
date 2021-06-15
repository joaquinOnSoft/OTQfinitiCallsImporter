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
			metadata.put(TITLE, "");
			metadata.put(ARTIST, "");
			//time = FileLength / (Sample Rate * Channels * Bits per sample /8)
			double duration = wavHeader.getChunkSize() / (wavHeader.getSampleRate() * wavHeader.getBitsPerSample() * wavHeader.getNumChannels()  / 8); 
			metadata.put(DURATION, Integer.toString((int) duration)); // In seconds
			metadata.put(SAMPLE_RATE, Integer.toString(wavHeader.getSampleRate()));
			int bits = wavHeader.getBitsPerSample() * wavHeader.getSubChunk1Size();
			metadata.put(BITS, Integer.toString(bits));
			metadata.put(CHANNELS, Integer.toString(wavHeader.getNumChannels()));
        }
        else {
        	log.warn("Audio header file not present or invalid");
        }
        
		return metadata;
	}

}
