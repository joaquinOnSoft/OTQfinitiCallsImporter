package com.opentext.qfiniti.importer.io;

import java.util.List;

import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.pojo.MappingConfig;

public interface IReader {
	public List<CallRecording> read(String filePath, MappingConfig config);
}
