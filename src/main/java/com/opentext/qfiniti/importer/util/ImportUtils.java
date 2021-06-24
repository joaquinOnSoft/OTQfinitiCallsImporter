package com.opentext.qfiniti.importer.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opentext.qfiniti.importer.IConfigGeneratorHeader;
import com.opentext.qfiniti.importer.io.filler.AbstractFiller;
import com.opentext.qfiniti.importer.io.transformer.AbstractTransformer;
import com.opentext.qfiniti.importer.pojo.CallRecording;

public class ImportUtils implements IConfigGeneratorHeader {
	private static final Logger log = LogManager.getLogger(ImportUtils.class);

	/**
	 * Assigns a value in a field based on the field Name
	 * 
	 * @param call      - Call recording object
	 * @param fieldName - Field name
	 * @param value     - Value to set in the field
	 * @return Call recording object with the field populated
	 */
	public static CallRecording setFieldValueByFieldName(CallRecording call, String fieldName, String value) {
		switch (fieldName) {
		case HEADER_PATH_NAME:
			call.setPathName(value);
			break;
		case HEADER_DATE_TIME:
			call.setDateTime(value);
			break;
		case HEADER_TEAM_MEMBER_NAME:
			call.setTeamMemberName(value);
			break;
		case HEADER_DURATION:
			call.setDuration(value);
			break;
		case HEADER_GROUP_HIERARCHY:
			call.setGroupHierachy(value);
			break;
		case HEADER_DNIS:
			call.setDnis(value);
			break;
		case HEADER_ANI:
			call.setAni(value);
			break;
		case HEADER_FILE_NAME:
			call.setFileName(value);
			break;
		default:
			call.addExtendedField(fieldName, value);	
		}
		
		return call;
	}

	public static String applyTransformer(String transformerName, String value, String path) {
		if (transformerName != null) {
			try {
				Class<?> tClass = Class.forName(transformerName);
				AbstractTransformer aTransformer = (AbstractTransformer) tClass.getConstructor(String.class).newInstance(path);
				value = aTransformer.transform(value);
			} catch (ClassNotFoundException e) {
				log.error("Transformer class not found: " + e.getLocalizedMessage());
			} catch (NoSuchMethodException e) {
				log.error("Not 'transform' method in Transformer class: " + e.getLocalizedMessage());
			} catch (SecurityException e) {
				log.error("Invalid Transformer (1): " + e.getLocalizedMessage());
			} catch (InstantiationException e) {
				log.error("Invalid Transformer (2): " + e.getLocalizedMessage());
			} catch (IllegalAccessException e) {
				log.error("Invalid Transformer (3): " + e.getLocalizedMessage());
			} catch (IllegalArgumentException e) {
				log.error("Invalid Transformer (4): " + e.getLocalizedMessage());
			} catch (InvocationTargetException e) {
				log.error("Invalid Transformer (5): " + e.getLocalizedMessage());
			}
		}

		return value;
	}

	public static String applyFiller(String fillerName, String callFullPath) {
		String value = null;

		if (fillerName != null) {
			try {
				Class<?> tClass = Class.forName(fillerName);
				AbstractFiller ifiller = (AbstractFiller) tClass.getDeclaredConstructor(String.class)
						.newInstance(callFullPath);
				value = ifiller.getValue();
			} catch (ClassNotFoundException e) {
				log.error("Filler class not found: " + e.getLocalizedMessage());
			} catch (NoSuchMethodException e) {
				log.error("Not 'getValue' method in Filler class: " + e.getLocalizedMessage());
			} catch (SecurityException e) {
				log.error("Invalid Filler (1): " + e.getLocalizedMessage());
			} catch (InstantiationException e) {
				log.error("Invalid Filler (2): " + e.getLocalizedMessage());
			} catch (IllegalAccessException e) {
				log.error("Invalid Filler (3): " + e.getLocalizedMessage());
			} catch (IllegalArgumentException e) {
				log.error("Invalid Filler (4): " + e.getLocalizedMessage());
			} catch (InvocationTargetException e) {
				log.error("Invalid Filler (5): " + e.getLocalizedMessage());
			}
		}

		return value;
	}
}
