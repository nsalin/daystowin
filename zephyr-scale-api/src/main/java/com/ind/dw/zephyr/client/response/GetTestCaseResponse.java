package com.ind.dw.zephyr.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ind.dw.zephyr.client.model.*;
import org.apache.logging.log4j.core.net.Priority;

import java.util.List;
import java.util.Map;

public class GetTestCaseResponse {
    @JsonProperty("id")
    private int id;

    @JsonProperty("key")
    private String key;

    @JsonProperty("name")
    private String name;

    @JsonProperty("project")
    private Project project;

    @JsonProperty("createdOn")
    private String createdOn;

    @JsonProperty("objective")
    private String objective;

    @JsonProperty("precondition")
    private String precondition;

    @JsonProperty("estimatedTime")
    private long estimatedTime;

    @JsonProperty("labels")
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
