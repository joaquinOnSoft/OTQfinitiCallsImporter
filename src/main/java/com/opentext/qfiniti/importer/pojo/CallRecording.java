/*
 *   (C) Copyright 2019 OpenText and others.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *   Contributors:
 *     Joaqu�n Garz�n - initial implementation
 *
 */
package com.opentext.qfiniti.importer.pojo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opentext.qfiniti.importer.IConfigGeneratorHeader;

public class CallRecording implements IConfigGeneratorHeader {
	private static final Logger log = LogManager.getLogger(CallRecording.class);

	public static final String DEFAULT_AGENT_NAME = "Juan Esposito Esposito";

	private String pathName;
	private String fileName;
	/** Duration in seconds */
	private int duration;
	private LocalDateTime dateTime;
	private String teamMemberName;
	private String groupHierachy;
	private String ani;
	private String dnis;

	private Map<String, String> extendedFields;

	public CallRecording() {
		this(null, null, 0);
	}

	public CallRecording(String pathName, String fileName, int duration) {
		this(pathName, fileName, duration, LocalDateTime.now());
	}

	public CallRecording(String pathName, String fileName, String duration) throws NumberFormatException {
		this(pathName, fileName, 0);
		setDuration(duration);
	}

	public CallRecording(String pathName, String fileName, String duration, LocalDateTime dateTime) {
		this(pathName, fileName, 0, dateTime);
		setDuration(duration);
	}

	public CallRecording(String pathName, String fileName, int duration, LocalDateTime dateTime) {
		this.pathName = pathName;
		this.fileName = fileName;
		this.duration = duration;
		this.dateTime = dateTime;

		extendedFields = new HashMap<String, String>();
	}

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getDuration() {
		return duration;
	}

	public String getDurationAsString() {
		return Integer.toString(duration);
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setDuration(String duration) throws NumberFormatException {
		try {
			this.duration = Integer.parseInt(duration);
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Invalid recording duration (in seconds): '" + duration + "'");
		}
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	/**
	 * Date and time that the recording was made.
	 * 
	 * Recommended format:
	 * 
	 * dd/MM/yyyy hh:mm:ss
	 * 
	 * Where: dd: Day of the Month mm: Month of the year yyyy: Year
	 * 
	 * @return
	 */
	public String getDateTimeAsString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

		return dateTime.format(formatter);
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * 
	 * @param dateTime in format "dd/MM/yyyy HH:mm:ss"
	 */
	public void setDateTime(String strDate) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
			this.dateTime = LocalDateTime.parse(strDate, formatter);
		} catch (IllegalArgumentException e) {
			log.error(e.getLocalizedMessage());
		}
	}

	public String getTeamMemberName() {
		return teamMemberName;
	}

	public void setDefaultTeamMemberName() {
		setTeamMemberName(DEFAULT_AGENT_NAME);
	}

	public void setTeamMemberName(String teamMemberName) {
		if (teamMemberName != null && !teamMemberName.contains(",")) {
			String[] sections = teamMemberName.split(" ");

			if (sections != null) {
				// NAME + FAMILY NAME 1 + FAMILY NAME 2 = 3 sections
				int nSections = sections.length;
				int nLastSection = nSections - 1;
				int nInit = 1;

				if (nSections > 3) {
					nInit = 2;
				}

				StringBuilder name = new StringBuilder();
				for (int i = nInit; i < nSections; i++) {
					name.append(sections[i]);
					if (i == nLastSection) {
						name.append(",");
					}

					name.append(" ");
				}

				for (int i = 0; i < nInit; i++) {
					name.append(sections[i]);
					if (i != (nInit - 1)) {
						name.append(" ");
					}
				}

				this.teamMemberName = name.toString();
				return;

			}

		}
		this.teamMemberName = teamMemberName;
	}

	public String getGroupHierachy() {
		return groupHierachy;
	}

	public void setGroupHierachy(String groupHierachy) {
		this.groupHierachy = groupHierachy;
	}

	public String getAni() {
		return ani;
	}

	public void setAni(String ani) {
		this.ani = ani;
	}

	public String getDnis() {
		return dnis;
	}

	public void setDnis(String dnis) {
		this.dnis = dnis;
	}

	/**
	 * Returns header titles for all the fields which has a value
	 * 
	 * @return Array with header titles for all the fields which has a value
	 */
	public String[] getHeaders() {
		List<String> header = new LinkedList<String>();

		header.add(HEADER_PATH_NAME);
		header.add(HEADER_FILE_NAME);
		header.add(HEADER_DATE_TIME);
		header.add(HEADER_DURATION);

		if (groupHierachy != null) {
			header.add(HEADER_GROUP_HIERARCHY);
		}

		if (teamMemberName != null) {
			header.add(HEADER_TEAM_MEMBER_NAME);
		}

		if (ani != null) {
			header.add(HEADER_ANI);
		}

		if (dnis != null) {
			header.add(HEADER_DNIS);
		}

		return header.toArray(new String[header.size()]);
	}

	/**
	 * Returns header titles for all the fields which has a value, excluding the
	 * headers provided as parameters.
	 * 
	 * @param excludedHeaders - Headers to be excluded from the list.
	 * @return Array with a filtered list of header titles
	 */
	public String[] getHeaders(List<String> excludedHeaders) {
		String[] headers = getHeaders();
		List<String> filteredHeaders = new LinkedList<String>();

		if (excludedHeaders != null && excludedHeaders.size() > 0) {
			for (String header : headers) {
				if (!excludedHeaders.contains(header)) {
					filteredHeaders.add(header);
				}
			}
		}

		return filteredHeaders.toArray(new String[filteredHeaders.size()]);
	}

	public void addExtendedField(String key, String value) {
		extendedFields.put(key, value);
	}

	public String getExtendedField(String key) {
		return extendedFields.get(key);
	}

	@Override
	public String toString() {
		// Path_Name File_Name Date_Time duration
		StringBuilder builder = new StringBuilder();
		// Path_Name File_Name Date_Time duration

		builder.append("{\n\t").append("Path_Name: ").append(pathName).append("\n\t").append("File_Name: ")
				.append(fileName).append("\n\t").append("Date_Time: ").append(getDateTimeAsString()).append("\n\t")
				.append("duration: ").append(duration).append("\n\t").append("Team_Member_Name: ")
				.append(teamMemberName).append("\n\t").append("group_hierarchy: ").append(groupHierachy).append("\n\t")
				.append("ani: ").append(ani).append("\n\t").append("dnis: ").append(dnis).append("\n").append("}");

		return builder.toString();
	}
}
