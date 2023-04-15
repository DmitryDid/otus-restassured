package com.otus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.otus.data.StatusInStoreData;
import lombok.Getter;

import java.util.List;

@Getter
public class PetDTO {

    @JsonProperty("id")
    Integer id;

    @JsonProperty("category")
    CategoryDTO category;

    @JsonProperty("name")
    String name;

    @JsonProperty("photoUrls")
    String photoUrls;

    @JsonProperty("tags")
    List<TagDTO> tags;

    @JsonProperty("status")
    StatusInStoreData status;

}
