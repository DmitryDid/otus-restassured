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

    PetDTO petBody;

    @Test
    @Description("Создание питомца без заполнения поля category")
    void addNewPetWithoutCategoryTest() {
        step("Создаем нового питомца");
        petBody = getPetBody();
        petBody.setCategory(null);
        CreatePetResponseDTO response = addNewPet(petBody);

        step("Проверяем данные в ответе");
        assertNull(response.getCategory());

        assertEquals(petBody.getName(), response.getName());
        assertEquals(1, response.getPhotoUrls().size());
        assertEquals(petBody.getPhotoUrls(), response.getPhotoUrls());
        assertEquals(1, response.getTags().size());
        assertEquals(2, response.getTags().get(0).getId());
        assertEquals(petBody.getTags().get(0).getName(), response.getTags().get(0).getName());
        assertEquals(AVAILABLE, response.getStatus());
    }

    @Test
    @Description("Создание питомца без заполнения поля name")
    void addNewPetWithoutNameTest() {
        step("Создаем нового питомца");
        petBody = getPetBody();
        petBody.setName(null);
        CreatePetResponseDTO response = addNewPet(petBody);

        step("Проверяем данные в ответе");
        assertNull(response.getName());

        assertEquals(petBody.getCategory().getId(), response.getCategory().getId());
        assertEquals(petBody.getCategory().getName(), response.getCategory().getName());
        assertEquals(1, response.getPhotoUrls().size());
        assertEquals(petBody.getPhotoUrls(), response.getPhotoUrls());
        assertEquals(1, response.getTags().size());
        assertEquals(2, response.getTags().get(0).getId());
        assertEquals(petBody.getTags().get(0).getName(), response.getTags().get(0).getName());
        assertEquals(AVAILABLE, response.getStatus());
    }

    @Test
    @Description("Создание питомца без заполнения поля tags")
    void addNewPetWithoutTagsTest() {
        step("Создаем нового питомца");
        petBody = getPetBody();
        petBody.setTags(null);
        CreatePetResponseDTO response = addNewPet(petBody);

        step("Проверяем данные в ответе");
        assertEquals(0, response.getTags().size());

        assertEquals(petBody.getCategory().getId(), response.getCategory().getId());
        assertEquals(petBody.getCategory().getName(), response.getCategory().getName());
        assertEquals(petBody.getName(), response.getName());
        assertEquals(1, response.getPhotoUrls().size());
        assertEquals(petBody.getPhotoUrls(), response.getPhotoUrls());
        assertEquals(AVAILABLE, response.getStatus());
    }

    @Test
    @Description("Создание питомца без заполнения поля status")
    void addNewPetWithoutStatusTest() {
        step("Создаем нового питомца");
        petBody = getPetBody();
        petBody.setStatus(null);
        CreatePetResponseDTO response = addNewPet(petBody);

        step("Проверяем данные в ответе");
        assertNull(response.getStatus());

        assertEquals(petBody.getCategory().getId(), response.getCategory().getId());
        assertEquals(petBody.getCategory().getName(), response.getCategory().getName());
        assertEquals(petBody.getName(), response.getName());
        assertEquals(1, response.getPhotoUrls().size());
        assertEquals(petBody.getPhotoUrls(), response.getPhotoUrls());
        assertEquals(1, response.getTags().size());
        assertEquals(2, response.getTags().get(0).getId());
        assertEquals(petBody.getTags().get(0).getName(), response.getTags().get(0).getName());
    }

    @Test
    @Description("Создание питомца без заполнения поля photoUrls")
    void addNewPetWithoutPhotoUrlsTest() {
        step("Создаем нового питомца");
        petBody = getPetBody();
        petBody.setPhotoUrls(null);
        CreatePetResponseDTO response = addNewPet(petBody);

        step("Проверяем данные в ответе");
        assertEquals(0, response.getPhotoUrls().size());

        assertEquals(petBody.getCategory().getId(), response.getCategory().getId());
        assertEquals(petBody.getCategory().getName(), response.getCategory().getName());
        assertEquals(petBody.getName(), response.getName());
        assertEquals(1, response.getTags().size());
        assertEquals(2, response.getTags().get(0).getId());
        assertEquals(petBody.getTags().get(0).getName(), response.getTags().get(0).getName());
        assertEquals(AVAILABLE, response.getStatus());
    }
}
