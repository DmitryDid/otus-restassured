package com.otus.data;

public enum OrderStatusData {

    PLACED("placed"),
    APPROVED("approved"),
    DELIVERED("delivered");

    final String name;

    OrderStatusData(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
