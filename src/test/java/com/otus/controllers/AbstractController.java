package com.otus.controllers;

import com.otus.extentions.Extension;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;

public abstract class AbstractController {

    static Response response;

    protected static Response isSuccess(Response response, boolean expect) {
        String statusCode = String.valueOf(response.getStatusCode());
        if (expect) {
            if (statusCode.startsWith("2")) {
                System.out.printf("SUCCESS: %s%n", statusCode);
                System.out.printf("body: %s%n%n", (response.asString().isEmpty() ? "empty" : response.asString()));
                return response;
            }
        } else {
            if (!statusCode.startsWith("2")) {
                System.out.printf("ERROR: %s%n", statusCode);
                System.out.printf("body: %s%n%n", (response.asString().isEmpty() ? "empty" : response.asString()));
                return response;
            }
        }
        Assertions.fail(statusCode + " " + response.asString());
        return response;
    }

    protected static RequestSpecification getBaseSpecification() {
        return RestAssured.given()
                .baseUri(Extension.BASE_URL)
                .contentType(ContentType.JSON)
                .log().method()
                .log().uri()
                .log().body();
    }
}
