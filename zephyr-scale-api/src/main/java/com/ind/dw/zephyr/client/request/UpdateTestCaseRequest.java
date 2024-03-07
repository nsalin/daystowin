package com.ind.dw.zephyr.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ind.dw.zephyr.client.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.core.net.Priority;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTestCaseRequest {
    private int id;
    private String key;
    private String name;
    @JsonProperty("project")
    private Project project;
    private String createdOn;
    private String objective;
    private String precondition;
    private long estimatedTime;
    private List<String> labels;
    @JsonProperty("component")
    private Component component;
    @JsonProperty("priority")
    private Priority priority;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("folder")
    private Folder folder;
    @JsonProperty("owner")
    private Owner owner;
    @JsonProperty("testScript")
    private TestScript testScript;
    @JsonProperty("customFields")
    private Map<String, Object> customFields;
    @JsonProperty("links")
    private Links links;
}

