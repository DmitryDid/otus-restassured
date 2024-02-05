package com.otus.data;

import lombok.Getter;

@Getter
public enum StatusInStoreData {

    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    final String name;

    StatusInStoreData(String name) {
        this.name = name;
    }

}
