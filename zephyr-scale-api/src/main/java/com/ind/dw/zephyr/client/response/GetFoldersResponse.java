package com.ind.dw.zephyr.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ind.dw.zephyr.client.model.Folder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetFoldersResponse {
    @JsonProperty("next")
    private String next;

    @JsonProperty("startAt")
    private int startAt;

    @JsonProperty("maxResults")
    private int maxResults;

    @JsonProperty("total")
    private int total;

    @JsonProperty("isLast")
    private boolean isLast;

    @JsonProperty("values")
    private List<FolderResponse> values;
}
