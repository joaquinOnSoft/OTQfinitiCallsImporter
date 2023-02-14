package com.opentext.qfiniti.importer.io.metadata;

public abstract class AbstractAudioMetadataExtractor implements IMetadataCreator {
	/**
	 * Provides the audio file duration in seconds
	 * <code>
	 *    time = FileLength / (Sample Rate * Channels * Bits per sample /8)
	 * </code>
	 * @param fileSize - File size 
	 * @param sampleRate - Audio sample rate
	 * @param channels - Number of channels
	 * @param bitsPerSample - Bits per sample
	 * @return Audio file duration in seconds
	 * 
	 * @see https://social.msdn.microsoft.com/Forums/windows/en-US/5a92be69-3b4e-4d92-b1d2-141ef0a50c91/how-to-calculate-duration-of-wave-file-from-its-size?forum=winforms
	 */
	protected float getDuration(long fileSize, int sampleRate, int channels, int bitsPerSample) {
		float timeInSecs = 0;
		if(sampleRate != 0 && channels != 0 && bitsPerSample != 0) {
			timeInSecs = fileSize / (sampleRate * channels * bitsPerSample / 8);
		}
		
		return timeInSecs;
	}
	
	protected float getDuration(long fileSize, String sampleRate, String channels, String bitsPerSample) {
		int nSampleRate = 0;
		int nChannels = 0;
		int nBitsPerSample = 0;
		
		if(sampleRate != null && channels != null && bitsPerSample != null) {
			try {
				nSampleRate = (int) Float.parseFloat(sampleRate);
				nChannels = (int) Float.parseFloat(channels);
				nBitsPerSample = (int) Float.parseFloat(bitsPerSample);
			} catch (NumberFormatException e) {
				log.warn("Invalid number, using default values (0)", e);
			}
		}
		
		return getDuration(fileSize, nSampleRate, nChannels, nBitsPerSample);
	}
}