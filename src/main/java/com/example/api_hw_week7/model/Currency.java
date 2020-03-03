package com.example.api_hw_week7.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Currency {

    @JsonProperty("currency")
    private String name;
    @JsonProperty("code")
    private String code;
    @JsonProperty("mid")
    private Double mid;

}
