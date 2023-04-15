package com.otus.tests;

import com.otus.base.TestBase;
import com.otus.controllers.PetController;
import com.otus.data.StatusInStoreData;
import com.otus.dto.PetDTO;
import com.otus.dto.PetGetDTO;
import com.otus.extentions.Extension;
import com.otus.helpers.BodyHelper;
import com.otus.helpers.GenerateDataHelper;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(Extension.class)
public class FindPetByIdTests extends TestBase {

    @Test
    @Description("Поиск существующего питомца")
    void findPetSuccessfulTest() {
        step("Создаем нового питомца");
        PetDTO body = BodyHelper.getPetBody();
        long petId = PetController.addNewPet(body).getId();

        step("Выполняем поиск созданного питомца");
        PetGetDTO findPet = PetController.findPetByID(petId);

        step("Проверяем данные в ответе");
        Assertions.assertEquals(petId, findPet.getId());
        Assertions.assertEquals(body.getCategory().getId(), findPet.getCategory().getId());
        Assertions.assertEquals(body.getCategory().getName(), findPet.getCategory().getName());
        Assertions.assertEquals(body.getName(), findPet.getName());
        Assertions.assertEquals(1, findPet.getPhotoUrls().size());
        Assertions.assertEquals(body.getPhotoUrls(), findPet.getPhotoUrls());
        Assertions.assertEquals(1, findPet.getTags().size());
        Assertions.assertEquals(2, findPet.getTags().get(0).getId());
        Assertions.assertEquals(body.getTags().get(0).getName(), findPet.getTags().get(0).getName());
        Assertions.assertEquals(StatusInStoreData.available, findPet.getStatus());
    }

    @Test
    @Description("Поиск не существующего питомца")
    void findNonExistPetTest() {
        long petId = GenerateDataHelper.getNewId();

        step("Выполняем поиск не существующего питомца");
        PetController.findPetByIDWithError(petId, 404);
    }
}
