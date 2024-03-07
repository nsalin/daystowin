package com.ind.dw.zephyr.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestCase {
    @JsonProperty("projectKey")
    private String projectKey;

    @JsonProperty("name")
    private String name;

    @JsonProperty("objective")
    private String objective;

    @JsonProperty("precondition")
    private String precondition;

    @JsonProperty("estimatedTime")
    private long estimatedTime;

    @JsonProperty("componentId")
    private int componentId;

    @JsonProperty("priorityName")
    private String priorityName;

    @JsonProperty("statusName")
    private String statusName;

    @JsonProperty("folderId")
    private int folderId;

    @JsonProperty("ownerId")
    private String ownerId;

    @JsonProperty("labels")
    private List<String> labels;

    @JsonProperty("customFields")
    private Map<String, Object> customFields;
}

