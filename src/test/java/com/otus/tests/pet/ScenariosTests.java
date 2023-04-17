package com.otus.tests.pet;

import com.otus.controllers.PetController;
import com.otus.dto.ApiResponseDTO;
import com.otus.dto.CreatePetResponseDTO;
import com.otus.dto.PetDTO;
import com.otus.dto.PetGetDTO;
import com.otus.extentions.Extension;
import com.otus.helpers.BodyHelper;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static com.otus.helpers.ConsoleHelper.step;

@ExtendWith(Extension.class)
public class ScenariosTests {

    @Test
    @Description("Поиск питомца")
    void scenario_1() {
        step("Создаем нового питомца в магазине");
        PetDTO petBody = BodyHelper.getPetBody();
        CreatePetResponseDTO newPet = PetController.addNewPet(petBody);

        step("Выполняем поиск созданного питомца по id");
        PetGetDTO findPet = PetController.findPetByID(newPet.getId());

        step("Сравниваем данные найденного и созданного питомца");
        Assertions.assertEquals(newPet.getId(), findPet.getId());
        Assertions.assertEquals(newPet.getCategory().getId(), findPet.getCategory().getId());
        Assertions.assertEquals(newPet.getCategory().getName(), findPet.getCategory().getName());
        Assertions.assertEquals(newPet.getName(), findPet.getName());
        Assertions.assertEquals(newPet.getPhotoUrls().size(), findPet.getPhotoUrls().size());
        Assertions.assertEquals(newPet.getPhotoUrls(), findPet.getPhotoUrls());
        Assertions.assertEquals(newPet.getTags().size(), findPet.getTags().size());
        Assertions.assertEquals(newPet.getTags().get(0).getId(), findPet.getTags().get(0).getId());
        Assertions.assertEquals(newPet.getTags().get(0).getName(), findPet.getTags().get(0).getName());
        Assertions.assertEquals(newPet.getStatus(), findPet.getStatus());

        step("Выполняем поиск созданного питомца по статусу");
        List<PetGetDTO> pets = PetController.findsPetsByStatus(newPet.getStatus());
        Assertions.assertTrue(pets.size() > 0);

        step("Проверяем что созданный питомец имеется среди найденных");
        pets = pets.stream()
                .filter(p -> p.getId().equals(newPet.getId()))
                .collect(Collectors.toList());
        Assertions.assertEquals(1, pets.size());

        step("Сравниваем данные найденного и созданного питомца");
        Assertions.assertEquals(newPet.getId(), pets.get(0).getId());
        Assertions.assertEquals(newPet.getCategory().getId(), pets.get(0).getCategory().getId());
        Assertions.assertEquals(newPet.getCategory().getName(), pets.get(0).getCategory().getName());
        Assertions.assertEquals(newPet.getName(), pets.get(0).getName());
        Assertions.assertEquals(newPet.getPhotoUrls().size(), pets.get(0).getPhotoUrls().size());
        Assertions.assertEquals(newPet.getPhotoUrls(), pets.get(0).getPhotoUrls());
        Assertions.assertEquals(newPet.getTags().size(), pets.get(0).getTags().size());
        Assertions.assertEquals(newPet.getTags().get(0).getId(), pets.get(0).getTags().get(0).getId());
        Assertions.assertEquals(newPet.getTags().get(0).getName(), pets.get(0).getTags().get(0).getName());
        Assertions.assertEquals(newPet.getStatus(), pets.get(0).getStatus());
    }

    @Test
    @Description("Удаление питомца")
    void scenario_2() {
        step("Создаем нового питомца в магазине");
        PetDTO petBody = BodyHelper.getPetBody();
        CreatePetResponseDTO newPet = PetController.addNewPet(petBody);

        step("Проверяем что созданный питомец имеется среди найденных");
        List<PetGetDTO> pets = PetController.findsPetsByStatus(newPet.getStatus()).stream()
                .filter(p -> p.getId().equals(newPet.getId()))
                .collect(Collectors.toList());
        Assertions.assertEquals(1, pets.size());

        step("Удаляем питомца");
        ApiResponseDTO deleteResponse = PetController.deletesPet(newPet.getId(), null);

        step("Проверяем данные в ответе");
        Assertions.assertEquals(200, deleteResponse.getCode());
        Assertions.assertEquals("unknown", deleteResponse.getType());
        Assertions.assertEquals(newPet.getId().toString(), deleteResponse.getMessage());

        step("Проверяем что созданный питомец отсутствует среди найденных");
        pets = PetController.findsPetsByStatus(newPet.getStatus()).stream()
                .filter(p -> p.getId().equals(newPet.getId()))
                .collect(Collectors.toList());
        Assertions.assertEquals(0, pets.size());
    }

    @Test
    @Description("Выгружаем фотографию питомца в магазин")
    void scenario_3() {
        step("Создаем нового питомца в магазине");
        PetDTO petBody = BodyHelper.getPetBody();
        CreatePetResponseDTO newPet = PetController.addNewPet(petBody);

        step("Обновляем питомцу фотографию");
        ApiResponseDTO uploadResponse = PetController.uploadImage(newPet.getId(), "Кот вырос",
                new File("src/test/resources/photos/cats_update_image.jpeg"));

        step("Проверяем данные в ответе");
        Assertions.assertEquals(200, uploadResponse.getCode());
        Assertions.assertEquals("unknown", uploadResponse.getType());
        Assertions.assertTrue(uploadResponse.getMessage().contains("File uploaded"));
    }
}
