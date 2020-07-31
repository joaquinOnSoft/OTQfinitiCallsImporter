package com.opentext.qfiniti.importer.io.filler;

public abstract class IFiller {
	public abstract String getValue();
	
	protected int getRandom(int min, int max) {
		return (int)(Math.random()*((max-min)+1))+min;		
	}
}
