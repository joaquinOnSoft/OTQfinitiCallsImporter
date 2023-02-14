package com.opentext.qfiniti.importer.io.metadata;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.kokorin.jaffree.ffprobe.FFprobe;
import com.github.kokorin.jaffree.ffprobe.FFprobeResult;
import com.github.kokorin.jaffree.ffprobe.Stream;

public class JaffreeMetadataExtractor implements IMetadataCreator {

	/**
	 * Reading wave format (.wav) header using 'Jaffree' library
	 * 
	 * <strong>NOTE: </strong>
	 * Jaffree stands for Java FFmpeg and FFprobe FREE command line wrapper. 
	 * Jaffree supports programmatic video production and consumption (with transparency)
	 * @see https://github.com/kokorin/Jaffree
	 */
	@Override
	public Map<String, String> extract(File audio) throws IOException {
		HashMap<String, String> metadata = null;
		
		FFprobeResult result = FFprobe.atPath()
			    .setShowStreams(true)
			    .setInput(audio.getAbsolutePath())
			    .execute();
		
		if(result != null) {
			List<Stream> streams = result.getStreams();
						
			if (streams != null && streams.size() > 0) {
				Stream stream = streams.get(0);
				
				metadata = new HashMap<String, String>();
				metadata.put(ENCODING, stream.getCodecName()); 
				metadata.put(DURATION, Integer.toString(stream.getDuration().intValue())); // In seconds
				metadata.put(SAMPLE_RATE, stream.getSampleRate().toString());
				metadata.put(BIT_RATE, stream.getBitRate().toString());
				metadata.put(BITS, stream.getBitsPerSample().toString());
				metadata.put(CHANNELS, stream.getChannels().toString());
			}
		}
				
		return metadata;
	}

}
