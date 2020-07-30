package com.opentext.qfiniti.importer.io;

import java.util.List;

import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.pojo.MappingConfig;

public interface IReader {
	
	public static final String COL_PATH_NAME = "Path_Name";	
	public static final String COL_DATE_TIME = "Date_Time";
	public static final String COL_TEAM_MEMBER = "Team_Member_Name";
	public static final String COL_DURATION = "duration";
	public static final String COL_GROUP_HIERARCHY = "group_hierarchy";
	public static final String COL_DNIS = "dnis";
	public static final String COL_ANI = "ANI";
	public static final String COL_FILE_NAME = "File_Name";

	public List<CallRecording> read(String filePath, MappingConfig config);
}
