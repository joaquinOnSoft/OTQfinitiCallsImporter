package com.opentext.qfiniti.importer.io.filler;

public class DnisFiller extends AbstractFiller {
	
	/**
	 *  Generate a random DNIS (Call type)
	 */
	@Override
	public String getValue() {
		int dnis[] = {1000, 2000, 3000, 4000, 5000};
		int index = getRandom(0, 4);
		
	    return String.format("%04d", dnis[index]);	
	}	
}
