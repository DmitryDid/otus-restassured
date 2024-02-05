package com.otus.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.otus.data.StatusInStoreData;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PetDTO {

    @JsonProperty("id")
    Long id;

    @JsonProperty("category")
    CategoryDTO category;

    @JsonProperty("name")
    String name;

    @JsonProperty("photoUrls")
    List<String> photoUrls;

    @JsonProperty("tags")
    List<TagDTO> tags;

    @JsonProperty("status")
    StatusInStoreData status;

}
