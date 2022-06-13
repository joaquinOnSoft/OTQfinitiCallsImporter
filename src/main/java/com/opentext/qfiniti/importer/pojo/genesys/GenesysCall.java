
package com.opentext.qfiniti.importer.pojo.genesys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "mediaType",
    "provider",
    "userIds",
    "startTime",
    "endTime",
    "durationMs",
    "initialDirection",
    "queueIds",
    "wrapupCodes",
    "organizationId",
    "conversationId",
    "conversationStartTime",
    "conversationEndTime",
    "recordingId",
    "filePath",
    "fileSize",
    "screenInformation"
})
@Generated("jsonschema2pojo")
public class GenesysCall {

    @JsonProperty("mediaType")
    private String mediaType;
    @JsonProperty("provider")
    private String provider;
    @JsonProperty("userIds")
    private List<String> userIds = null;
    @JsonProperty("startTime")
    private String startTime;
    @JsonProperty("endTime")
    private String endTime;
    @JsonProperty("durationMs")
    private Integer durationMs;
    @JsonProperty("initialDirection")
    private String initialDirection;
    @JsonProperty("queueIds")
    private List<String> queueIds = null;
    @JsonProperty("wrapupCodes")
    private List<String> wrapupCodes = null;
    @JsonProperty("organizationId")
    private String organizationId;
    @JsonProperty("conversationId")
    private String conversationId;
    @JsonProperty("conversationStartTime")
    private String conversationStartTime;
    @JsonProperty("conversationEndTime")
    private String conversationEndTime;
    @JsonProperty("recordingId")
    private String recordingId;
    @JsonProperty("filePath")
    private String filePath;
    @JsonProperty("fileSize")
    private Integer fileSize;
    @JsonProperty("screenInformation")
    private ScreenInformation screenInformation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("mediaType")
    public String getMediaType() {
        return mediaType;
    }

    @JsonProperty("mediaType")
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @JsonProperty("provider")
    public String getProvider() {
        return provider;
    }

    @JsonProperty("provider")
    public void setProvider(String provider) {
        this.provider = provider;
    }

    @JsonProperty("userIds")
    public List<String> getUserIds() {
        return userIds;
    }

    @JsonProperty("userIds")
    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    @JsonProperty("startTime")
    public String getStartTime() {
        return startTime;
    }

    @JsonProperty("startTime")
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("endTime")
    public String getEndTime() {
        return endTime;
    }

    @JsonProperty("endTime")
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @JsonProperty("durationMs")
    public Integer getDurationMs() {
        return durationMs;
    }

    @JsonProperty("durationMs")
    public void setDurationMs(Integer durationMs) {
        this.durationMs = durationMs;
    }

    @JsonProperty("initialDirection")
    public String getInitialDirection() {
        return initialDirection;
    }

    @JsonProperty("initialDirection")
    public void setInitialDirection(String initialDirection) {
        this.initialDirection = initialDirection;
    }

    @JsonProperty("queueIds")
    public List<String> getQueueIds() {
        return queueIds;
    }

    @JsonProperty("queueIds")
    public void setQueueIds(List<String> queueIds) {
        this.queueIds = queueIds;
    }

    @JsonProperty("wrapupCodes")
    public List<String> getWrapupCodes() {
        return wrapupCodes;
    }

    @JsonProperty("wrapupCodes")
    public void setWrapupCodes(List<String> wrapupCodes) {
        this.wrapupCodes = wrapupCodes;
    }

    @JsonProperty("organizationId")
    public String getOrganizationId() {
        return organizationId;
    }

    @JsonProperty("organizationId")
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    @JsonProperty("conversationId")
    public String getConversationId() {
        return conversationId;
    }

    @JsonProperty("conversationId")
    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    @JsonProperty("conversationStartTime")
    public String getConversationStartTime() {
        return conversationStartTime;
    }

    @JsonProperty("conversationStartTime")
    public void setConversationStartTime(String conversationStartTime) {
        this.conversationStartTime = conversationStartTime;
    }

    @JsonProperty("conversationEndTime")
    public String getConversationEndTime() {
        return conversationEndTime;
    }

    @JsonProperty("conversationEndTime")
    public void setConversationEndTime(String conversationEndTime) {
        this.conversationEndTime = conversationEndTime;
    }

    @JsonProperty("recordingId")
    public String getRecordingId() {
        return recordingId;
    }

    @JsonProperty("recordingId")
    public void setRecordingId(String recordingId) {
        this.recordingId = recordingId;
    }

    @JsonProperty("filePath")
    public String getFilePath() {
        return filePath;
    }

    @JsonProperty("filePath")
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @JsonProperty("fileSize")
    public Integer getFileSize() {
        return fileSize;
    }

    @JsonProperty("fileSize")
    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    @JsonProperty("screenInformation")
    public ScreenInformation getScreenInformation() {
        return screenInformation;
    }

    @JsonProperty("screenInformation")
    public void setScreenInformation(ScreenInformation screenInformation) {
        this.screenInformation = screenInformation;
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
