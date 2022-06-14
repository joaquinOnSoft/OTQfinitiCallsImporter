/*
 *   (C) Copyright 2022 OpenText and others.
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
 *     Joaquín Garzón - initial implementation
 *
 */
package com.opentext.qfiniti.importer.io;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opentext.qfiniti.importer.pojo.CallRecording;
import com.opentext.qfiniti.importer.pojo.FieldMapping;
import com.opentext.qfiniti.importer.pojo.MappingConfig;
import com.opentext.qfiniti.importer.pojo.genesys.GenesysCall;

public class JSONReader extends AbstractReader {
	
    public String invokeGetter(Object obj, String variableName)
    {
    	String value = null;
    	
        try {
            PropertyDescriptor pd = new PropertyDescriptor(variableName, obj.getClass());
            Method getter = pd.getReadMethod();
            Object f = getter.invoke(obj);
            value = f.toString();
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | IntrospectionException e) {
			log.error(e.getMessage());
        }
        
        return value;
    }	
	
	/**
	 * Read a Comma-Separated Values (CSV) file which file is just a normal
	 * plain-text file, store data in column by column, and split it by a separator
	 * (e.g normally it is a comma "," or a ";").
	 * 
	 * @see http://zetcode.com/java/opencsv/
	 * @see https://www.baeldung.com/java-csv-file-array
	 * @see https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
	 */
	@Override
	public List<CallRecording> read(String filePath, MappingConfig config) {
		recordings = new LinkedList<CallRecording>();
		CallRecording call = null;

		GenesysCall genesysCall = null;

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			genesysCall = objectMapper.readValue(new File(filePath), GenesysCall.class);
		} catch (IOException e) {
			log.error(e.getMessage());
		}

		call = new CallRecording();

		String parentFolder = (new File(filePath)).getParent();
				
		for(FieldMapping field: config.getFieldMapping()) {
			log.debug(field.getIname() + "\t" + invokeGetter(genesysCall, field.getIname()));	
			call = mapField(call, invokeGetter(genesysCall, field.getIname()), config.getFieldMappingByIName(field.getIname()), parentFolder);
		}
		
		call = generateField(call, config.getFieldFiller());
					
		recordings.add(call);		
		
		return recordings;
	}
}
