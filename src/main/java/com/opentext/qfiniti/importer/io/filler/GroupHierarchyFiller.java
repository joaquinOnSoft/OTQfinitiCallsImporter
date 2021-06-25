package com.opentext.qfiniti.importer.io.filler;

import java.io.File;

import com.opentext.qfiniti.importer.pojo.CallRecording;

public class GroupHierarchyFiller extends AbstractFiller {

	public GroupHierarchyFiller(CallRecording call, String path, String fileName) {
		super(call, path, fileName);
	}

	public GroupHierarchyFiller(CallRecording call, String filePath) {
		super(call, filePath);
	}

	public GroupHierarchyFiller(CallRecording call, File file) {
		super(call, file);
	}

	@Override
	public String getValue() {
		return "Client-i";
	}
}
