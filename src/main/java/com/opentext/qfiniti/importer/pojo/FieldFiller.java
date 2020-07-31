
package com.opentext.qfiniti.importer.pojo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "oname",
    "ovalue",
    "filler"
})
public class FieldFiller {

    @JsonProperty("oname")
    private String oname;
    @JsonProperty("ovalue")
    private String ovalue;
    @JsonProperty("filler")
    private String filler;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("oname")
    public String getOname() {
        return oname;
    }

    @JsonProperty("oname")
    public void setOname(String oname) {
        this.oname = oname;
    }

    @JsonProperty("ovalue")
    public String getOvalue() {
        return ovalue;
    }

    @JsonProperty("ovalue")
    public void setOvalue(String ovalue) {
        this.ovalue = ovalue;
    }

    @JsonProperty("filler")
    public String getFiller() {
        return filler;
    }

    @JsonProperty("filler")
    public void setFiller(String filler) {
        this.filler = filler;
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
