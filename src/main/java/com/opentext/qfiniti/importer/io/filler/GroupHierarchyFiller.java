package com.opentext.qfiniti.importer.io.filler;

import java.io.File;

public class GroupHierarchyFiller extends AbstractFiller {

	public GroupHierarchyFiller(String path, String fileName) {
		super(path, fileName);
	}

	public GroupHierarchyFiller(String filePath) {
		super(filePath);
	}

	public GroupHierarchyFiller(File file) {
		super(file);
	}

	@Override
	public String getValue() {
		return "Client-i";
	}
}
