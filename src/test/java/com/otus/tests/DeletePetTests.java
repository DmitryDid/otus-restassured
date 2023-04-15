package com.otus.tests;

import com.otus.base.TestBase;
import com.otus.controllers.PetController;
import com.otus.dto.ApiResponseDTO;
import com.otus.extentions.Extension;
import com.otus.helpers.BodyHelper;
import com.otus.helpers.GenerateDataHelper;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(Extension.class)
public class DeletePetTests extends TestBase {

    @Test
    @Description("Успешное удаление питомца")
    void deletePetSuccessfulTest() {
        step("Создаем нового питомца");
        long petID = PetController.addNewPet(BodyHelper.getPetBody()).getId();

        step("Удаляем питомца");
        ApiResponseDTO response = PetController.deletesPet(petID, null);

        step("Проверяем данные в ответе");
        Assertions.assertEquals(200, response.getCode());
        Assertions.assertEquals("unknown", response.getType());
        Assertions.assertEquals(petID + "", response.getMessage());
    }

    @Test
    @Description("Повторное удаление питомца")
    void reDeletePetTest() {
        step("Создаем нового питомца");
        long petID = PetController.addNewPet(BodyHelper.getPetBody()).getId();

        step("Удаляем питомца");
        PetController.deletesPet(petID, null);

        step("Повторно удаляем питомца");
        PetController.deletesPetWithError(petID, null, 404);
    }

    @Test
    @Description("Удаляем несуществующего питомца")
    void deleteNonExistPetTest() {
        step("Создаем нового питомца");
        long petID = GenerateDataHelper.getNewId();

        step("Удаляем питомца");
        PetController.deletesPetWithError(petID, null, 404);
    }
}
