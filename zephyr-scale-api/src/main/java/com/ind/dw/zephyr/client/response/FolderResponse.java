package com.ind.dw.zephyr.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ind.dw.zephyr.client.model.Project;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FolderResponse {
    @JsonProperty("id")
    private int id;

    @JsonProperty("parentId")
    private Integer parentId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("index")
    private int index;

    @JsonProperty("folderType")
    private String folderType;

    @JsonProperty("project")
    private Project project;
}
