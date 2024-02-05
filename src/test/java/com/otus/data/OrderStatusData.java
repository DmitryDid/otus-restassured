package com.otus.data;

import lombok.Getter;

@Getter
public enum OrderStatusData {

    PLACED("placed"),
    APPROVED("approved"),
    DELIVERED("delivered");

    final String name;

    OrderStatusData(String name) {
        this.name = name;
    }

}
