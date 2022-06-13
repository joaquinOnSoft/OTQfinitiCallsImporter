package com.opentext.qfiniti.importer.io.transformer;

public class DateyyyyMMddTHHmmssSSSTransformer extends AbstractDateTransformer {

	// 2022-06-06T11:04:15.746+0000
	private static final String DATE_FORMAT_YYYY_MM_DDTHH_MM_SS_SSS = "yyyy-MM-dd'T'HH:mm:ss.SSS'+'0000";	
	
	public DateyyyyMMddTHHmmssSSSTransformer(String path) {
		super(path);
	}

	@Override
	public String transform(String strDate) {
		return transformDateWithOptionalTime(strDate, DATE_FORMAT_YYYY_MM_DDTHH_MM_SS_SSS);
	}

}
