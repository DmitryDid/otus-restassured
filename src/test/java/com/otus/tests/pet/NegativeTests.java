package com.otus.tests.pet;

import com.otus.extentions.Extension;
import com.otus.helpers.ConsoleHelper;
import com.otus.helpers.GenerateDataHelper;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.otus.controllers.PetController.*;
import static com.otus.helpers.BodyHelper.getPetBody;
import static com.otus.helpers.ConsoleHelper.step;

@ExtendWith(Extension.class)
public class NegativeTests {

    @Test
    @Description("Повторное удаление питомца")
    void reDeletePetTest() {
        step("Создаем нового питомца");
        long petID = addNewPet(getPetBody()).getId();

        ConsoleHelper.step("Удаляем питомца");
        deletesPet(petID, null);

        step("Повторно удаляем питомца");
        deletesPetWithError(petID, null, 404);
    }

    @Test
    @Description("Удаляем несуществующего питомца")
    void deleteNonExistPetTest() {
        step("Создаем нового питомца");
        long petID = GenerateDataHelper.getNewId();

        step("Удаляем питомца");
        deletesPetWithError(petID, null, 404);
    }

    @Test
    @Description("Поиск не существующего питомца")
    void findNonExistPetTest() {
        long petId = GenerateDataHelper.getNewId();

        step("Выполняем поиск не существующего питомца");
        findPetByIDWithError(petId, 404);
    }
}
