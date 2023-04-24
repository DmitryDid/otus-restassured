package com.otus.tests.pet;

import com.otus.controllers.PetController;
import com.otus.data.StatusInStoreData;
import com.otus.dto.CreatePetResponseDTO;
import com.otus.dto.PetDTO;
import com.otus.extentions.Extension;
import com.otus.helpers.BodyHelper;
import com.otus.helpers.ConsoleHelper;
import jdk.jfr.Description;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(Extension.class)
public class CheckFieldsTests {

    @Test
    @Description("Создание питомца без заполнения поля category")
    void addNewPetWithoutCategoryTest() {
        ConsoleHelper.step("Создаем нового питомца");
        PetDTO body = BodyHelper.getPetBody();
        body.setCategory(null);
        CreatePetResponseDTO response = PetController.addNewPet(body);

        ConsoleHelper.step("Проверяем данные в ответе");
        Assertions.assertNull(response.getCategory());

        Assertions.assertEquals(body.getName(), response.getName());
        Assertions.assertEquals(1, response.getPhotoUrls().size());
        Assertions.assertEquals(body.getPhotoUrls(), response.getPhotoUrls());
        Assertions.assertEquals(1, response.getTags().size());
        Assertions.assertEquals(2, response.getTags().get(0).getId());
        Assertions.assertEquals(body.getTags().get(0).getName(), response.getTags().get(0).getName());
        Assertions.assertEquals(StatusInStoreData.AVAILABLE, response.getStatus());
    }

    @Test
    @Description("Создание питомца без заполнения поля name")
    void addNewPetWithoutNameTest() {
        ConsoleHelper.step("Создаем нового питомца");
        PetDTO body = BodyHelper.getPetBody();
        body.setName(null);
        CreatePetResponseDTO response = PetController.addNewPet(body);

        ConsoleHelper.step("Проверяем данные в ответе");
        Assertions.assertNull(response.getName());

        Assertions.assertEquals(response.getCategory().getId(), response.getCategory().getId());
        Assertions.assertEquals(body.getCategory().getName(), response.getCategory().getName());
        Assertions.assertEquals(1, response.getPhotoUrls().size());
        Assertions.assertEquals(body.getPhotoUrls(), response.getPhotoUrls());
        Assertions.assertEquals(1, response.getTags().size());
        Assertions.assertEquals(2, response.getTags().get(0).getId());
        Assertions.assertEquals(body.getTags().get(0).getName(), response.getTags().get(0).getName());
        Assertions.assertEquals(StatusInStoreData.AVAILABLE, response.getStatus());
    }

    @Test
    @Description("Создание питомца без заполнения поля tags")
    void addNewPetWithoutTagsTest() {
        ConsoleHelper.step("Создаем нового питомца");
        PetDTO body = BodyHelper.getPetBody();
        body.setTags(null);
        CreatePetResponseDTO response = PetController.addNewPet(body);

        ConsoleHelper.step("Проверяем данные в ответе");
        Assertions.assertEquals(0, response.getTags().size());

        Assertions.assertEquals(response.getCategory().getId(), response.getCategory().getId());
        Assertions.assertEquals(body.getCategory().getName(), response.getCategory().getName());
        Assertions.assertEquals(body.getName(), response.getName());
        Assertions.assertEquals(1, response.getPhotoUrls().size());
        Assertions.assertEquals(body.getPhotoUrls(), response.getPhotoUrls());
        Assertions.assertEquals(StatusInStoreData.AVAILABLE, response.getStatus());
    }

    @Test
    @Description("Создание питомца без заполнения поля status")
    void addNewPetWithoutStatusTest() {
        ConsoleHelper.step("Создаем нового питомца");
        PetDTO body = BodyHelper.getPetBody();
        body.setStatus(null);
        CreatePetResponseDTO response = PetController.addNewPet(body);

        ConsoleHelper.step("Проверяем данные в ответе");
        Assertions.assertNull(response.getStatus());

        Assertions.assertEquals(response.getCategory().getId(), response.getCategory().getId());
        Assertions.assertEquals(body.getCategory().getName(), response.getCategory().getName());
        Assertions.assertEquals(body.getName(), response.getName());
        Assertions.assertEquals(1, response.getPhotoUrls().size());
        Assertions.assertEquals(body.getPhotoUrls(), response.getPhotoUrls());
        Assertions.assertEquals(1, response.getTags().size());
        Assertions.assertEquals(2, response.getTags().get(0).getId());
        Assertions.assertEquals(body.getTags().get(0).getName(), response.getTags().get(0).getName());
    }

    @Test
    @Description("Создание питомца без заполнения поля photoUrls")
    void addNewPetWithoutPhotoUrlsTest() {
        ConsoleHelper.step("Создаем нового питомца");
        PetDTO body = BodyHelper.getPetBody();
        body.setPhotoUrls(null);
        CreatePetResponseDTO response = PetController.addNewPet(body);

        ConsoleHelper.step("Проверяем данные в ответе");
        Assertions.assertEquals(0, response.getPhotoUrls().size());

        Assertions.assertEquals(response.getCategory().getId(), response.getCategory().getId());
        Assertions.assertEquals(body.getCategory().getName(), response.getCategory().getName());
        Assertions.assertEquals(body.getName(), response.getName());
        Assertions.assertEquals(1, response.getTags().size());
        Assertions.assertEquals(2, response.getTags().get(0).getId());
        Assertions.assertEquals(body.getTags().get(0).getName(), response.getTags().get(0).getName());
        Assertions.assertEquals(StatusInStoreData.AVAILABLE, response.getStatus());
    }
}
