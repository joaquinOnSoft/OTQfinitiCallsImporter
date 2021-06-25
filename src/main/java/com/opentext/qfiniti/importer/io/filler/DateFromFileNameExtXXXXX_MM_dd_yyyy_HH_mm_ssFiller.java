package com.opentext.qfiniti.importer.io.filler;

import java.io.File;
import java.text.ParseException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.util.DateUtil;

public class DateFromFileNameExtXXXXX_MM_dd_yyyy_HH_mm_ssFiller extends AbstractFiller {

	private static final Logger log = LogManager.getLogger(DateFromFileNameExtXXXXX_MM_dd_yyyy_HH_mm_ssFiller.class);

	public DateFromFileNameExtXXXXX_MM_dd_yyyy_HH_mm_ssFiller(CallRecording call, File file) {
		super(call, file);
	}

	public DateFromFileNameExtXXXXX_MM_dd_yyyy_HH_mm_ssFiller(CallRecording call, String path, String fileName) {
		super(call, path, fileName);
	}

	public DateFromFileNameExtXXXXX_MM_dd_yyyy_HH_mm_ssFiller(CallRecording call, String filePath) {
		super(call, filePath);
	}

	/**
	 * Get the date from the file name that match the following pattern:
	 * 
	 * <stron>ExtXXXXX_MM_dd_yyyy_HH;mm;ss</strong>
	 * 
	 * Some examples:
	 * <ul>
	 * <li>ext42094_01_14_2016_16;29;15.wav</li>
	 * <li>ext42098_03_30_2015_11;35;46.wav</li>
	 * <li>ext42110_02_16_2017_15;39;00.wav</li>
	 * </ul>
	 */
	@Override
	public String getValue() {
		String dateStr = null;

		if (file != null) {
			String fileName = file.getName();
			int index = fileName.indexOf("_");
			if (index > 0) {
				dateStr = fileName.substring(index + 1);

				// "MM_dd_yyyy_HH;mm;ss" --> "MM_dd_yyyy_HH%3bmm%3bss"
				dateStr = dateStr.replace("%3b", ";");

				try {
					Date date = DateUtil.strToDate(dateStr, "MM_dd_yyyy_HH;mm;ss");
					dateStr = DateUtil.dateToQfinitiFormat(date);
				} catch (ParseException e) {
					log.warn("Invalid file name. It must math the pattern 'ExtXXXXX_MM_dd_yyyy_HH;mm;ss'");
				}
			}
		}

		return dateStr;
	}
}
