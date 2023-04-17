package com.otus.extentions;

import com.otus.helpers.ClearHelper;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class Extension implements BeforeAllCallback, AfterAllCallback {

    public static final String BASE_URL = System.getProperty("baseUrl");

    @Override
    public void beforeAll(ExtensionContext context) {
        RestAssured.defaultParser = Parser.JSON;
        System.out.println("BASE_URL: " + BASE_URL);
    }

    @Override
    public void afterAll(ExtensionContext context) {
        ClearHelper.deleteAllPets();
        ClearHelper.deleteAllUsers();
    }
}