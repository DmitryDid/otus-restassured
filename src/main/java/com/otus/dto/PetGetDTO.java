package com.otus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.otus.data.StatusInStoreData;
import lombok.Getter;

import java.util.List;

@Getter
public class PetGetDTO {

    @JsonProperty("id")
    Long id;

    @JsonProperty("category")
    CategoryGetDTO category;

    @JsonProperty("name")
    String name;

    @JsonProperty("photoUrls")
    List<String> photoUrls;

    @JsonProperty("tags")
    List<TagGetDTO> tags;

    @JsonProperty("status")
    StatusInStoreData status;

}
