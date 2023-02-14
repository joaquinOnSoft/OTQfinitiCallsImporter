package com.opentext.qfiniti.importer.io.metadata;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PureJavaMetadataExtractor implements IMetadataCreator {

	/***
	 * Gets the duration time of wav file In Java
	 * @see https://gist.github.com/edwardyoon/0ac61cc393c50918474921b69629e668
	 */
	@Override
	public Map<String, String> extract(File audio) throws IOException {
		HashMap<String, String> metadata = new HashMap<String, String>();
		
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(audio);
		} catch (UnsupportedAudioFileException | IOException e) {
        	log.warn("Audio header file not present or invalid");
		}
		
		if(audioInputStream != null) {
			AudioFormat format = audioInputStream.getFormat();
			long audioFileLength = audio.length();
			int frameSize = format.getFrameSize();
			float frameRate = format.getFrameRate();
			float durationInSeconds = (audioFileLength / (frameSize * frameRate));
			float bits = format.getSampleSizeInBits();
			float sampleRate =  format.getSampleRate();
			
			metadata.put(ENCODING, format.getEncoding().toString());
			metadata.put(SAMPLE_RATE, Integer.toString((int) sampleRate));
			metadata.put(DURATION, Integer.toString((int) durationInSeconds));
			metadata.put(BITS, Integer.toString((int) bits));
			metadata.put(CHANNELS, Integer.toString(format.getChannels()));
		}
		
		return metadata;
	}

}
