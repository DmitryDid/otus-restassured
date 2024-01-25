package com.otus.tests.pet;

import com.otus.dto.CreatePetResponseDTO;
import com.otus.dto.PetDTO;
import com.otus.extentions.Extension;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.otus.controllers.PetController.addNewPet;
import static com.otus.data.StatusInStoreData.AVAILABLE;
import static com.otus.helpers.BodyHelper.getPetBody;
import static com.otus.helpers.ConsoleHelper.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(Extension.class)
public class CheckFieldsTests {

    PetDTO body;

    @Test
    @Description("Создание питомца без заполнения поля category")
    void addNewPetWithoutCategoryTest() {
        step("Создаем нового питомца");
        body = getPetBody();
        body.setCategory(null);
        CreatePetResponseDTO response = addNewPet(body);

        step("Проверяем данные в ответе");
        assertNull(response.getCategory());

        assertEquals(body.getName(), response.getName());
        assertEquals(1, response.getPhotoUrls().size());
        assertEquals(body.getPhotoUrls(), response.getPhotoUrls());
        assertEquals(1, response.getTags().size());
        assertEquals(2, response.getTags().get(0).getId());
        assertEquals(body.getTags().get(0).getName(), response.getTags().get(0).getName());
        assertEquals(AVAILABLE, response.getStatus());
    }

    @Test
    @Description("Создание питомца без заполнения поля name")
    void addNewPetWithoutNameTest() {
        step("Создаем нового питомца");
        body = getPetBody();
        body.setName(null);
        CreatePetResponseDTO response = addNewPet(body);

        step("Проверяем данные в ответе");
        assertNull(response.getName());

        assertEquals(response.getCategory().getId(), response.getCategory().getId());
        assertEquals(body.getCategory().getName(), response.getCategory().getName());
        assertEquals(1, response.getPhotoUrls().size());
        assertEquals(body.getPhotoUrls(), response.getPhotoUrls());
        assertEquals(1, response.getTags().size());
        assertEquals(2, response.getTags().get(0).getId());
        assertEquals(body.getTags().get(0).getName(), response.getTags().get(0).getName());
        assertEquals(AVAILABLE, response.getStatus());
    }

    @Test
    @Description("Создание питомца без заполнения поля tags")
    void addNewPetWithoutTagsTest() {
        step("Создаем нового питомца");
        body = getPetBody();
        body.setTags(null);
        CreatePetResponseDTO response = addNewPet(body);

        step("Проверяем данные в ответе");
        assertEquals(0, response.getTags().size());

        assertEquals(response.getCategory().getId(), response.getCategory().getId());
        assertEquals(body.getCategory().getName(), response.getCategory().getName());
        assertEquals(body.getName(), response.getName());
        assertEquals(1, response.getPhotoUrls().size());
        assertEquals(body.getPhotoUrls(), response.getPhotoUrls());
        assertEquals(AVAILABLE, response.getStatus());
    }

    @Test
    @Description("Создание питомца без заполнения поля status")
    void addNewPetWithoutStatusTest() {
        step("Создаем нового питомца");
        body = getPetBody();
        body.setStatus(null);
        CreatePetResponseDTO response = addNewPet(body);

        step("Проверяем данные в ответе");
        assertNull(response.getStatus());

        assertEquals(response.getCategory().getId(), response.getCategory().getId());
        assertEquals(body.getCategory().getName(), response.getCategory().getName());
        assertEquals(body.getName(), response.getName());
        assertEquals(1, response.getPhotoUrls().size());
        assertEquals(body.getPhotoUrls(), response.getPhotoUrls());
        assertEquals(1, response.getTags().size());
        assertEquals(2, response.getTags().get(0).getId());
        assertEquals(body.getTags().get(0).getName(), response.getTags().get(0).getName());
    }

    @Test
    @Description("Создание питомца без заполнения поля photoUrls")
    void addNewPetWithoutPhotoUrlsTest() {
        step("Создаем нового питомца");
        body = getPetBody();
        body.setPhotoUrls(null);
        CreatePetResponseDTO response = addNewPet(body);

        step("Проверяем данные в ответе");
        assertEquals(0, response.getPhotoUrls().size());

        assertEquals(response.getCategory().getId(), response.getCategory().getId());
        assertEquals(body.getCategory().getName(), response.getCategory().getName());
        assertEquals(body.getName(), response.getName());
        assertEquals(1, response.getTags().size());
        assertEquals(2, response.getTags().get(0).getId());
        assertEquals(body.getTags().get(0).getName(), response.getTags().get(0).getName());
        assertEquals(AVAILABLE, response.getStatus());
    }
}
