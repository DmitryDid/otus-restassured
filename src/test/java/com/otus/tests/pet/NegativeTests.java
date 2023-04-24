package com.otus.tests.pet;

import com.otus.controllers.PetController;
import com.otus.extentions.Extension;
import com.otus.helpers.BodyHelper;
import com.otus.helpers.ConsoleHelper;
import com.otus.helpers.GenerateDataHelper;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(Extension.class)
public class NegativeTests {

    @Test
    @Description("Повторное удаление питомца")
    void reDeletePetTest() {
        ConsoleHelper.step("Создаем нового питомца");
        long petID = PetController.addNewPet(BodyHelper.getPetBody()).getId();

        ConsoleHelper.step("Удаляем питомца");
        PetController.deletesPet(petID, null);

        ConsoleHelper.step("Повторно удаляем питомца");
        PetController.deletesPetWithError(petID, null, 404);
    }

    @Test
    @Description("Удаляем несуществующего питомца")
    void deleteNonExistPetTest() {
        ConsoleHelper.step("Создаем нового питомца");
        long petID = GenerateDataHelper.getNewId();

        ConsoleHelper.step("Удаляем питомца");
        PetController.deletesPetWithError(petID, null, 404);
    }

    @Test
    @Description("Поиск не существующего питомца")
    void findNonExistPetTest() {
        long petId = GenerateDataHelper.getNewId();

        ConsoleHelper.step("Выполняем поиск не существующего питомца");
        PetController.findPetByIDWithError(petId, 404);
    }
}
