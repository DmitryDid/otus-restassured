package com.otus.data;

public enum StatusInStoreData {

    AVAILABLE("available"),
    PENDING("pending"),
    SOLD("sold");

    final String name;

    StatusInStoreData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
