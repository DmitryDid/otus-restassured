package com.otus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateUserResponseDTO {

    @JsonProperty("id")
    Long id;

    @JsonProperty("photoUrls")
    List<String> photoUrls;

    @JsonProperty("tags")
    List<String> tags;

}
