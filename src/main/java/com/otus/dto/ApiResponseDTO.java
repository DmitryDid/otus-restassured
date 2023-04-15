package com.otus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ApiResponseDTO {

    @JsonProperty("code")
    Integer code;

    @JsonProperty("type")
    String type;

    @JsonProperty("message")
    String message;

}
