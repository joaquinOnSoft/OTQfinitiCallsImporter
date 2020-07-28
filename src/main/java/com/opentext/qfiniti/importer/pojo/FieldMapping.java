
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
    "iname",
    "itype",
    "mapped",
    "iformat",
    "oname",
    "otype",
    "oformat"
})
public class FieldMapping {

    @JsonProperty("iname")
    private String iname;
    @JsonProperty("itype")
    private String itype;
    @JsonProperty("mapped")
    private String mapped;
    @JsonProperty("iformat")
    private String iformat;
    @JsonProperty("oname")
    private String oname;
    @JsonProperty("otype")
    private String otype;
    @JsonProperty("oformat")
    private String oformat;
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

    @JsonProperty("iformat")
    public String getIformat() {
        return iformat;
    }

    @JsonProperty("iformat")
    public void setIformat(String iformat) {
        this.iformat = iformat;
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

    @JsonProperty("oformat")
    public String getOformat() {
        return oformat;
    }

    @JsonProperty("oformat")
    public void setOformat(String oformat) {
        this.oformat = oformat;
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
