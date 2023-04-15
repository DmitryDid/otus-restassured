package com.otus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserDTO {

    @JsonProperty("id")
    Integer id;

    @JsonProperty("username")
    String username;

    @JsonProperty("firstName")
    String firstName;

    @JsonProperty("lastName")
    String lastName;

    @JsonProperty("email")
    String email;

    @JsonProperty("password")
    String password;

    @JsonProperty("phone")
    String phone;

    @JsonProperty("userStatus")
    Integer userStatus;

}
