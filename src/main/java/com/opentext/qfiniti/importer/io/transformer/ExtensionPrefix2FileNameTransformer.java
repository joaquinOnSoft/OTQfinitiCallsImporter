package com.opentext.qfiniti.importer.io.transformer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExtensionPrefix2FileNameTransformer extends AbstractTransformer {
	
	public ExtensionPrefix2FileNameTransformer(String path) {
		super(path);
	}

	/**
	 * Find a call recording file name from a prefix, e.g. from and prefix like
	 * <strong>ext2960006643_05_04_2021_12;40;57</strong> will search a file in the 
	 * provided path that match the given prefix:
	 * 
	 * ext2960006643_05_04_2021_12;40;57_18638_it1483yw.wav
	 * 
	 * @param prefix - Call recording file name prefix
	 * @return call recording file name
	 * 
	 * @see https://mkyong.com/java/java-files-walk-examples/#find-files-by-filename
	 */
	@Override
	public String transform(String prefix) {			
		List<Path> result = null;
		
		try (Stream<Path> walk = Files.walk(Path.of(path))) {
			result = walk.filter(Files::isRegularFile)   // is a file
             	.filter(p -> p.getFileName().toString().startsWith(prefix))
             	.collect(Collectors.toList());
        } catch (IOException e) {
			log.error("", e);
			result = null;
		}
		
		log.debug("transform(" + prefix +") :" + result);
		
		return result != null && result.size() > 0? result.get(0).toFile().getName() : null;
	}

}
