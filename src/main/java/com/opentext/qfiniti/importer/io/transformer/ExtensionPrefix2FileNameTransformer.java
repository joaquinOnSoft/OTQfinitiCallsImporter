package com.opentext.qfiniti.importer.io.transformer;

public class ExtensionPrefix2FileNameTransformer extends AbstractTransformer {
	
	public ExtensionPrefix2FileNameTransformer(String path) {
		super(path);
	}

	/**
	 * Generates a call recording file name from a prefix, e.g. from and prefix like
	 * <strong>ext2960006643_05_04_2021_12;40;57</strong> will search a file in the 
	 * provided path that match the given prefix:
	 * 
	 * ext2960006643_05_04_2021_12;40;57_18638_it1483yw.wav
	 * 
	 * @param prefix - Call recording file name prefix
	 * @return call recording file name
	 */
	@Override
	public String transform(String prefix) {
		StringBuilder builder = new StringBuilder();
		builder.append(prefix);

		return builder.toString();
	}

}
