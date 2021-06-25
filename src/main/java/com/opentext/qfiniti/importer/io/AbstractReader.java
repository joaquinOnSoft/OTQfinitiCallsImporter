package com.opentext.qfiniti.importer.io;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.pojo.FieldFiller;
import com.opentext.qfiniti.importer.pojo.FieldMapping;
import com.opentext.qfiniti.importer.util.ImportUtils;

public abstract class AbstractReader implements IReader {

	protected static final Logger log = LogManager.getLogger(AbstractReader.class);
	protected List<CallRecording> recordings = null;

	/**
	 * 
	 * @param call - Call recording information 
	 * @param value - 
	 * @param fMapping - Field Mapping information 
	 * (provides the mapping between the input and the output fields)
	 * @param path - Path where the call recording is stored
	 * @return
	 */
	protected CallRecording mapField(CallRecording call, String value, FieldMapping fMapping, String path) {
		if (fMapping.isMapped()) {
			String transformerName = fMapping.getTransformer();
			if (transformerName != null) {
				value = ImportUtils.applyTransformer(transformerName, value, path);
			}

			call = ImportUtils.setFieldValueByFieldName(call, fMapping.getOname(), value);
		} else {
			call.addExtendedField(fMapping.getIname(), value);
		}

		return call;
	}

	/**
	 * Add field generated automatically with a 'filler' or a default value
	 * 
	 * @param call     - Call recording
	 * @param fFillers - Field filler with the instruction to populate the field
	 *                 with a default value or a random generated value.
	 * @return Call recording
	 */
	protected CallRecording generateField(CallRecording call, List<FieldFiller> fFillers) {

		for (FieldFiller filler : fFillers) {
			String value = filler.getOvalue();

			if (filler.getFiller() != null) {
				String callFullPath = call.getPathName() + File.separator + call.getFileName();
				value = ImportUtils.applyFiller(filler.getFiller(), call, callFullPath);
			}

			call = ImportUtils.setFieldValueByFieldName(call, filler.getOname(), value);
		}

		return call;
	}
}
