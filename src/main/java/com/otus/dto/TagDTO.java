package com.otus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TagDTO {

    @JsonProperty("id")
    Integer id;

    @JsonProperty("name")
    String name;

}
