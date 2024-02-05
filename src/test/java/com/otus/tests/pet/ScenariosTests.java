package com.otus.tests.pet;

import com.otus.dto.ApiResponseDTO;
import com.otus.dto.CreatePetResponseDTO;
import com.otus.dto.PetDTO;
import com.otus.dto.PetGetDTO;
import com.otus.extentions.Extension;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import static com.otus.controllers.PetController.*;
import static com.otus.helpers.BodyHelper.getPetBody;
import static com.otus.helpers.ConsoleHelper.step;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(Extension.class)
public class ScenariosTests {

    PetDTO petBody;

    @Test
    @Description("Поиск питомца")
    void scenario_1() {
        step("Создаем нового питомца в магазине");
        petBody = getPetBody();
        CreatePetResponseDTO newPet = addNewPet(petBody);

        step("Выполняем поиск созданного питомца по id");
        PetGetDTO findPet = findPetByID(newPet.getId());

        step("Сравниваем данные найденного и созданного питомца");
        assertEquals(newPet.getId(), findPet.getId());
        assertEquals(newPet.getCategory().getId(), findPet.getCategory().getId());
        assertEquals(newPet.getCategory().getName(), findPet.getCategory().getName());
        assertEquals(newPet.getName(), findPet.getName());
        assertEquals(newPet.getPhotoUrls().size(), findPet.getPhotoUrls().size());
        assertEquals(newPet.getPhotoUrls(), findPet.getPhotoUrls());
        assertEquals(newPet.getTags().size(), findPet.getTags().size());
        assertEquals(newPet.getTags().get(0).getId(), findPet.getTags().get(0).getId());
        assertEquals(newPet.getTags().get(0).getName(), findPet.getTags().get(0).getName());
        assertEquals(newPet.getStatus(), findPet.getStatus());

        step("Выполняем поиск созданного питомца по статусу");
        List<PetGetDTO> pets = findsPetsByStatus(newPet.getStatus());
        assertFalse(pets.isEmpty());

        step("Проверяем что созданный питомец имеется среди найденных");
        pets = pets.stream()
                .filter(p -> p.getId().equals(newPet.getId()))
                .collect(Collectors.toList());
        assertEquals(1, pets.size());

        step("Сравниваем данные найденного и созданного питомца");
        assertEquals(newPet.getId(), pets.get(0).getId());
        assertEquals(newPet.getCategory().getId(), pets.get(0).getCategory().getId());
        assertEquals(newPet.getCategory().getName(), pets.get(0).getCategory().getName());
        assertEquals(newPet.getName(), pets.get(0).getName());
        assertEquals(newPet.getPhotoUrls().size(), pets.get(0).getPhotoUrls().size());
        assertEquals(newPet.getPhotoUrls(), pets.get(0).getPhotoUrls());
        assertEquals(newPet.getTags().size(), pets.get(0).getTags().size());
        assertEquals(newPet.getTags().get(0).getId(), pets.get(0).getTags().get(0).getId());
        assertEquals(newPet.getTags().get(0).getName(), pets.get(0).getTags().get(0).getName());
        assertEquals(newPet.getStatus(), pets.get(0).getStatus());
    }

    @Test
    @Description("Удаление питомца")
    void scenario_2() {
        step("Создаем нового питомца в магазине");
        petBody = getPetBody();
        CreatePetResponseDTO newPet = addNewPet(petBody);

        step("Проверяем что созданный питомец имеется среди найденных");
        List<PetGetDTO> pets = findsPetsByStatus(newPet.getStatus()).stream()
                .filter(p -> p.getId().equals(newPet.getId()))
                .collect(Collectors.toList());
        assertEquals(1, pets.size());

        step("Удаляем питомца");
        ApiResponseDTO deleteResponse = deletesPet(newPet.getId(), null);

        step("Проверяем данные в ответе");
        assertEquals(200, deleteResponse.getCode());
        assertEquals("unknown", deleteResponse.getType());
        assertEquals(newPet.getId().toString(), deleteResponse.getMessage());

        step("Проверяем что созданный питомец отсутствует среди найденных");
        pets = findsPetsByStatus(newPet.getStatus()).stream()
                .filter(p -> p.getId().equals(newPet.getId()))
                .collect(Collectors.toList());
        assertEquals(0, pets.size());
    }

    @Test
    @Description("Выгружаем фотографию питомца в магазин")
    void scenario_3() {
        step("Создаем нового питомца в магазине");
        petBody = getPetBody();
        CreatePetResponseDTO newPet = addNewPet(petBody);

        step("Обновляем питомцу фотографию");
        ApiResponseDTO uploadResponse = uploadImage(newPet.getId(), "Кот вырос",
                new File("src/test/resources/photos/cats_update_image.jpeg"));

        step("Проверяем данные в ответе");
        assertEquals(200, uploadResponse.getCode());
        assertEquals("unknown", uploadResponse.getType());
        assertTrue(uploadResponse.getMessage().contains("File uploaded"));
    }
}
