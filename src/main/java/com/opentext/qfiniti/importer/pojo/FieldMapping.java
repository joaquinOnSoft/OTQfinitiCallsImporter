
package com.opentext.qfiniti.importer.pojo;

import java.util.HashMap;
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
@JsonPropertyOrder({ "iname", "itype", "mapped", "oname", "otype", "transformer" })
public class FieldMapping {
	private static final String DATA_TYPE_STRING = "string";

	@JsonProperty("iname")
	private String iname;
	@JsonProperty("itype")
	private String itype = DATA_TYPE_STRING;
	@JsonProperty("mapped")
	private String mapped;
	@JsonProperty("oname")
	private String oname;
	@JsonProperty("otype")
	private String otype = DATA_TYPE_STRING;
	@JsonProperty("transformer")
	private String transformer;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("iname")
	public String getIname() {
		return iname;
	}

	@JsonProperty("iname")
	public void setIname(String iname) {
		this.iname = iname;
	}

	@JsonProperty("itype")
	public String getItype() {
		return itype;
	}

	@JsonProperty("itype")
	public void setItype(String itype) {
		this.itype = itype;
	}

	@JsonProperty("mapped")
	public String getMapped() {
		return mapped;
	}

	@JsonProperty("mapped")
	public void setMapped(String mapped) {
		this.mapped = mapped;
	}

	public boolean isMapped() {
		return Boolean.parseBoolean(mapped);
	}

	@JsonProperty("oname")
	public String getOname() {
		return oname;
	}

	@JsonProperty("oname")
	public void setOname(String oname) {
		this.oname = oname;
	}

	@JsonProperty("otype")
	public String getOtype() {
		return otype;
	}

	@JsonProperty("otype")
	public void setOtype(String otype) {
		this.otype = otype;
	}

	@JsonProperty("transformer")
	public String getTransformer() {
		return transformer;
	}

	@JsonProperty("transformer")
	public void setTransformer(String transformer) {
		this.transformer = transformer;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
