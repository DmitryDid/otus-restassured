package com.otus.tests.user;

import com.otus.controllers.UserController;
import com.otus.dto.ApiResponseDTO;
import com.otus.dto.UserDTO;
import com.otus.dto.UserGetDTO;
import com.otus.extentions.Extension;
import com.otus.helpers.BodyHelper;
import io.restassured.response.Response;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.otus.helpers.ConsoleHelper.step;

@ExtendWith(Extension.class)
public class ScenariosTests {

    @Test
    @Description("Создание и поиск пользователя")
    void scenario_1() {
        step("Создаем нового пользователя");
        UserDTO userBody = BodyHelper.getUserBody();
        ApiResponseDTO response = UserController.createUser(userBody);

        step("Проверяем данные в ответе");
        Assertions.assertEquals(200, response.getCode());
        Assertions.assertEquals("unknown", response.getType());
        Assertions.assertFalse(response.getMessage().isEmpty());

        step("Получаем созданного пользователя");
        UserGetDTO getUser = UserController.getUserBUsername(userBody.getUsername());

        step("Сравниваем данные полученного и созданного пользователя");
        Assertions.assertEquals(userBody.getUsername(), getUser.getUsername());
        Assertions.assertEquals(userBody.getFirstName(), getUser.getFirstName());
        Assertions.assertEquals(userBody.getLastName(), getUser.getLastName());
        Assertions.assertEquals(userBody.getEmail(), getUser.getEmail());
        Assertions.assertEquals(userBody.getPassword(), getUser.getPassword());
        Assertions.assertEquals(userBody.getPhone(), getUser.getPhone());
        Assertions.assertEquals(userBody.getUserStatus(), getUser.getUserStatus());
    }

    @Test
    @Description("Удаление пользователя")
    void scenario_2() {
        step("Создаем нового пользователя");
        UserDTO userBody = BodyHelper.getUserBody();
        ApiResponseDTO response = UserController.createUser(userBody);
        Assertions.assertEquals(200, response.getCode());

        step("Удаляем пользователя");
        ApiResponseDTO deleteResponse = UserController.deleteUser(userBody.getUsername());

        step("Проверяем данные в ответе");
        Assertions.assertEquals(200, deleteResponse.getCode());
        Assertions.assertEquals("unknown", deleteResponse.getType());
        Assertions.assertEquals(userBody.getUsername(), deleteResponse.getMessage());

        step("Получаем созданного пользователя");
        Response getUserResponse = UserController.getUserBUsernameAsResponse(userBody.getUsername());

        step("Проверяем код ответа");
        Assertions.assertEquals(404, getUserResponse.getStatusCode());

        step("Проверяем данные в ответе");
        ApiResponseDTO getUser = getUserResponse.as(ApiResponseDTO.class);
        Assertions.assertEquals(1, getUser.getCode());
        Assertions.assertEquals("error", getUser.getType());
        Assertions.assertEquals("User not found", getUser.getMessage());
    }
}
