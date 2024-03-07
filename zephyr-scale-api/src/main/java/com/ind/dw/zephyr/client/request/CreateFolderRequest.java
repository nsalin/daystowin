package com.ind.dw.zephyr.client.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ind.dw.zephyr.client.model.TestCycle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFolderRequest {

    @JsonProperty("parentId")
    private Integer parentId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("projectKey")
    private String projectKey;

    @JsonProperty("folderType")
    private String folderType;

    public CreateFolderRequest(String projectKey, String folderName) {
        this.projectKey = projectKey;
        this.name = folderName;
    }
}
