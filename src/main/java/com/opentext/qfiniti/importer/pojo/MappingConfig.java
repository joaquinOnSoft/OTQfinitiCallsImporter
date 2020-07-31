
package com.opentext.qfiniti.importer.pojo;

import java.util.HashMap;
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
@JsonPropertyOrder({
    "inputType",
    "fieldFiller",
    "fieldMapping"
})
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

}
