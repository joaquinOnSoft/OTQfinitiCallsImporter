package com.opentext.qfiniti.importer.io.transformer;

import org.junit.Before;

public abstract class AbstractTransformerTest {
	protected String path;
	
	@Before // setup()
    public void before() {
		path =  System.getProperty("user.dir");
	}
}
