package com.opentext.qfiniti.importer.io.filler;

public class AniFiller extends AbstractFiller {
	
	/**
	 * Generate a random ANI (Client phone number) 
	 **/
	@Override
	public String getValue() {
	    return String.format("6%08d", getRandom(1, 99999999));
	}
}
