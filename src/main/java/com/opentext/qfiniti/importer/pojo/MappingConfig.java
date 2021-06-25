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
 *     Joaquín Garzón - initial implementation
 *
 */
package com.opentext.qfiniti.importer.pojo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Generated with http://www.jsonschema2pojo.org/
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "inputType", "fieldFiller", "fieldMapping" })
public class MappingConfig {

	@JsonProperty("inputType")
	private String inputType;
	@JsonProperty("fieldFiller")
	private List<FieldFiller> fieldFiller = null;
	@JsonProperty("fieldMapping")
	private List<FieldMapping> fieldMapping = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("inputType")
	public String getInputType() {
		return inputType;
	}

	@JsonProperty("inputType")
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	@JsonProperty("fieldFiller")
	public List<FieldFiller> getFieldFiller() {
		return fieldFiller;
	}

	@JsonProperty("fieldFiller")
	public void setFieldFiller(List<FieldFiller> fieldFiller) {
		this.fieldFiller = fieldFiller;
	}

	@JsonProperty("fieldMapping")
	public List<FieldMapping> getFieldMapping() {
		return fieldMapping;
	}

	@JsonProperty("fieldMapping")
	public void setFieldMapping(List<FieldMapping> fieldMapping) {
		this.fieldMapping = fieldMapping;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	public String[] getColumnNames() {
		List<String> columns = new LinkedList<String>();

		for (FieldMapping field : fieldMapping) {
			if (field.isMapped() && field.getOname() != null) {
				columns.add(field.getOname());
			}
		}

		for (FieldFiller field : fieldFiller) {
			if (field.getOname() != null) {
				columns.add(field.getOname());
			}
		}

		String[] columnsArray = new String[columns.size()];
		return columns.toArray(columnsArray);
	}

}
