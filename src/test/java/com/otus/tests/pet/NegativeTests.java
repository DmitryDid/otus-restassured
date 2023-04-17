package com.otus.tests.pet;

import com.otus.controllers.PetController;
import com.otus.extentions.Extension;
import com.otus.helpers.BodyHelper;
import com.otus.helpers.GenerateDataHelper;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.otus.helpers.ConsoleHelper.step;

@ExtendWith(Extension.class)
public class NegativeTests {

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

    @Test
    @Description("Поиск не существующего питомца")
    void findNonExistPetTest() {
        long petId = GenerateDataHelper.getNewId();

        step("Выполняем поиск не существующего питомца");
        PetController.findPetByIDWithError(petId, 404);
    }
}
