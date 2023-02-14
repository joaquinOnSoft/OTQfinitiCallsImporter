package com.opentext.qfiniti.importer.io.metadata;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.github.kokorin.jaffree.ffprobe.FFprobe;
import com.github.kokorin.jaffree.ffprobe.FFprobeResult;
import com.github.kokorin.jaffree.ffprobe.Stream;
import com.opentext.qfiniti.importer.util.OSValidator;

public class JaffreeMetadataExtractor implements IMetadataCreator {

	/**
	 * Reading wave format (.wav) header using 'Jaffree' library
	 * 
	 * <strong>NOTE: </strong> Jaffree stands for Java FFmpeg and FFprobe FREE
	 * command line wrapper. Jaffree supports programmatic video production and
	 * consumption (with transparency)
	 * 
	 * @see https://github.com/kokorin/Jaffree
	 */
	@Override
	public Map<String, String> extract(File audio) throws IOException {
		HashMap<String, String> metadata = null;

		String ffmpegPath = null;
		
		if(isFfmpegInPath()) {
			ffmpegPath = "";
		}
		else {
			ffmpegPath = System.getProperty("FFMPEG_BIN");
			if (ffmpegPath == null) {
				ffmpegPath = System.getenv("FFMPEG_BIN");
			}
		}

		if (ffmpegPath != null) {
			FFprobeResult result = FFprobe.atPath().setShowStreams(true).setInput(audio.getAbsolutePath()).execute();

			if (result != null) {
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
		}

		return metadata;
	}

	/**
	 * Check existence of `ffprobe` in the PATH.
	 * 
	 * This method is operative system independent!!!
	 */ 
	protected boolean isFfmpegInPath() {
		// `ffprobe` executable name can change depending on the operative system
		String exec = "ffprobe";

		if (OSValidator.isWindows()) {
			exec = "ffprobe.exe";
		}

		return isFfmpegInPath(exec);
	}

	/**
	 * Check existence of `ffprobe` in the PATH
	 * 
	 * <strong>NOTE<strong>: 
	 * @see https://stackoverflow.com/questions/934191/how-to-check-existence-of-a-program-in-the-path
	 * @see com.opentext.qfiniti.importer.io.metadata.JaffreeMetadataExtractor#isFfmpegInPath
	 **/
	private boolean isFfmpegInPath(String exec) {
		 return java.util.stream.Stream
				.of(System.getenv("PATH").split(Pattern.quote(File.pathSeparator)))
				.map(Paths::get)
				.anyMatch(path -> Files.exists(path.resolve(exec)));
	}
}
