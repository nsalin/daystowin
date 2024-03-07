package com.ind.dw.zephyr.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateTestCaseResponse {
    @JsonProperty("errorCode")
    private int errorCode;
    @JsonProperty("message")
    private String message;
}
