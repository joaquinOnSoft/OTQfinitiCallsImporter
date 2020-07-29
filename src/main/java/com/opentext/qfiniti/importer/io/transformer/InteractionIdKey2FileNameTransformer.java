package com.opentext.qfiniti.importer.io.transformer;

public class InteractionIdKey2FileNameTransformer implements ITransformer {
	private static final String CALL_RECORDING_FILE_PREFIX = "IRCall_";
	private static final String CALL_RECORDING_FILE_SUFIX = ".wav";
	
	/**
	 * Generates a call recording file name from an id, e.g. from and
	 * Id like 2001788444D0191021 will generate a file name like:
	 * 
	 * 		IRCall_2001788444D0191021.wav
	 * 
	 * @param id - Call recording id
	 * @return call recording file name
	 */	
	@Override
	public String transform(String id) {
		StringBuilder builder = new StringBuilder();
		builder.append(CALL_RECORDING_FILE_PREFIX)
			.append(id)
			.append(CALL_RECORDING_FILE_SUFIX);
		
		return builder.toString();
	}

}
