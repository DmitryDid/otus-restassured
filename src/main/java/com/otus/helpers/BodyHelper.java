package com.otus.helpers;

import com.otus.data.StatusInStoreData;
import com.otus.dto.CategoryDTO;
import com.otus.dto.PetDTO;
import com.otus.dto.TagDTO;

import java.util.Collections;

public class BodyHelper {

    public static PetDTO getPetBody() {
        return PetDTO.builder()
                .category(CategoryDTO.builder()
                        .id(1)
                        .name("кот")
                        .build())
                .name("Васька")
                .status(StatusInStoreData.available)
                .photoUrls(Collections.singletonList("https://s13.stc.yc.kpcdn.net/share/i/instagram/B44solahwlo/wr-1280.webp"))
                .tags(Collections.singletonList(TagDTO.builder()
                        .id(2)
                        .name("кошки")
                        .build()))
                .build();
    }
}
