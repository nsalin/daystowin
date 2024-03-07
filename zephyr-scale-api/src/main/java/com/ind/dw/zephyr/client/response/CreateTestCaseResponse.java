package com.ind.dw.zephyr.client.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateTestCaseResponse {

    @JsonProperty("id")
    private int id;
    @JsonProperty("self")
    private String self;
    @JsonProperty("key")
    private String key;
}
