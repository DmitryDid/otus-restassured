package com.otus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TagGetDTO {

    @JsonProperty("id")
    Integer id;

    @JsonProperty("name")
    String name;

}
