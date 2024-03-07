package com.ind.dw.zephyr.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Component {
        @JsonProperty("id")
        private int id;

    }
