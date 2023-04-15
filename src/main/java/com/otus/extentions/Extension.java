package com.otus.extentions;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class Extension implements BeforeAllCallback {

    public static final String BASE_URL = System.getProperty("baseUrl");

    @Override
    public void beforeAll(ExtensionContext context) {
        System.out.println("BASE_URL: " + BASE_URL);
    }
}