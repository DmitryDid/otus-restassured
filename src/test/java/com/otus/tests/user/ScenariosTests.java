package com.otus.tests.user;

import com.otus.dto.ApiResponseDTO;
import com.otus.dto.UserDTO;
import com.otus.dto.UserGetDTO;
import com.otus.extentions.Extension;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.otus.controllers.UserController.*;
import static com.otus.helpers.BodyHelper.getUserBody;
import static com.otus.helpers.ConsoleHelper.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(Extension.class)
public class ScenariosTests {

    UserDTO userBody;

    @Test
    @Description("Создание и поиск пользователя")
    void scenario_1() {
        step("Создаем нового пользователя");
        userBody = getUserBody();
        ApiResponseDTO response = createUser(userBody);

        step("Проверяем данные в ответе");
        assertEquals(200, response.getCode());
        assertEquals("unknown", response.getType());
        assertFalse(response.getMessage().isEmpty());

        step("Получаем созданного пользователя");
        UserGetDTO getUser = getUserBUsername(userBody.getUsername());

        step("Сравниваем данные полученного и созданного пользователя");
        assertEquals(userBody.getUsername(), getUser.getUsername());
        assertEquals(userBody.getFirstName(), getUser.getFirstName());
        assertEquals(userBody.getLastName(), getUser.getLastName());
        assertEquals(userBody.getEmail(), getUser.getEmail());
        assertEquals(userBody.getPassword(), getUser.getPassword());
        assertEquals(userBody.getPhone(), getUser.getPhone());
        assertEquals(userBody.getUserStatus(), getUser.getUserStatus());
    }

    @Test
    @Description("Удаление пользователя")
    void scenario_2() {
        step("Создаем нового пользователя");
        userBody = getUserBody();
        ApiResponseDTO response = createUser(userBody);
        assertEquals(200, response.getCode());

        step("Удаляем пользователя");
        ApiResponseDTO deleteResponse = deleteUser(userBody.getUsername());

        step("Проверяем данные в ответе");
        assertEquals(200, deleteResponse.getCode());
        assertEquals("unknown", deleteResponse.getType());
        assertEquals(userBody.getUsername(), deleteResponse.getMessage());

        step("Получаем созданного пользователя");
        Response getUserResponse = getUserBUsernameAsResponse(userBody.getUsername());

        step("Проверяем код ответа");
        assertEquals(404, getUserResponse.getStatusCode());

        step("Проверяем данные в ответе");
        ApiResponseDTO getUser = getUserResponse.as(ApiResponseDTO.class);
        assertEquals(1, getUser.getCode());
        assertEquals("error", getUser.getType());
        assertEquals("User not found", getUser.getMessage());
    }
}
