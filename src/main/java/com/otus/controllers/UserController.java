package com.otus.controllers;

import com.otus.dto.ApiResponseDTO;
import com.otus.dto.UserDTO;
import com.otus.dto.UserGetDTO;
import com.otus.helpers.ClearHelper;
import io.restassured.response.Response;

public class UserController extends AbstractController {

    // create user
    public static ApiResponseDTO createUser(UserDTO body) {
        response = isSuccess(getBaseSpecification()
                        .body(body)
                        .post("/user")
                        .then().extract().response(),
                true);
        ApiResponseDTO result = response.as(ApiResponseDTO.class);
        ClearHelper.createdUsersId.put(body.getUsername(), 1);
        return result;
    }

    // delete user
    public static Response deleteUserAsResponse(String userName) {
        return getBaseSpecification()
                .delete("/user/{userName}", userName)
                .then().extract().response();
    }

    public static ApiResponseDTO deleteUser(String userName) {
        response = isSuccess(getBaseSpecification()
                        .delete("/user/{userName}", userName)
                        .then().extract().response(),
                true);
        return response.as(ApiResponseDTO.class);
    }

    // get user by username
    public static UserGetDTO getUserBUsername(String userName) {
        response = isSuccess(getBaseSpecification()
                        .get("/user/{userName}", userName)
                        .then().extract().response(),
                true);
        return response.as(UserGetDTO.class);
    }

    public static Response getUserBUsernameAsResponse(String userName) {
        return getBaseSpecification()
                .get("/user/{userName}", userName)
                .then().extract().response();
    }
}
