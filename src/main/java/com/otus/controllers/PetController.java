package com.otus.controllers;

import com.otus.data.StatusInStoreData;
import com.otus.dto.ApiResponseDTO;
import com.otus.dto.CreatePetResponseDTO;
import com.otus.dto.PetDTO;
import com.otus.dto.PetGetDTO;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetController extends AbstractController {

    // uploads an image
    public static ApiResponseDTO uploadImage(Integer petId, String additionalMetadata, File file) {
        response = isSuccess(getBaseSpecification()
                        .pathParam("petId", petId)
                        .formParam("additionalMetadata", additionalMetadata)
                        .multiPart(file)
                        .post("/pet/1/uploadImage")
                        .then().extract().response(),
                true);
        return response.as(ApiResponseDTO.class);
    }

    // Add a new pet to the store
    public static CreatePetResponseDTO addNewPet(PetDTO body) {
        response = isSuccess(getBaseSpecification()
                        .body(body)
                        .post("/pet")
                        .then().extract().response(),
                true);
        return response.as(CreatePetResponseDTO.class);
    }

    // Update an existing pet
    public static CreatePetResponseDTO updatePet(PetDTO body) {
        response = isSuccess(getBaseSpecification()
                        .body(body)
                        .put("/pet")
                        .then().extract().response(),
                true);
        return response.as(CreatePetResponseDTO.class);
    }

    // Finds Pets by status
    public static List<PetDTO> findsPetsByStatus(StatusInStoreData status) {
        response = isSuccess(getBaseSpecification()
                        .queryParams("status", status)
                        .get("/pet")
                        .then().extract().response(),
                true);
        return Arrays.asList(response.as(PetDTO[].class));
    }

    // Find pet by ID
    public static PetGetDTO findPetByID(Long petId) {
        response = isSuccess(getBaseSpecification()
                        .get("/pet/" + ((petId == null) ? "" : petId))
                        .then().extract().response(),
                true);
        return response.as(PetGetDTO.class);
    }

    public static void findPetByIDWithError(Long petId, Integer statusCode) {
        response = isSuccess(getBaseSpecification()
                        .get("/pet/" + ((petId == null) ? "" : petId))
                        .then().extract().response(),
                false);
        Assertions.assertEquals(statusCode, response.getStatusCode());
    }

    // Updates a pet in the store with form data
    public static ApiResponseDTO updatesPetInStore(Integer petId, String name, String status) {
        response = isSuccess(getBaseSpecification()
                        .pathParam("petId", petId)
                        .formParam("name", name)
                        .formParam("status", status)
                        .post("/pet")
                        .then().extract().response(),
                true);
        return response.as(ApiResponseDTO.class);
    }

    // Deletes a pet
    public static ApiResponseDTO deletesPet(Long petId, String apiKey) {
        Map<String, String> headers = new HashMap<>();
        if (apiKey != null) headers.put("api_key", apiKey);

        response = isSuccess(getBaseSpecification()
                        .headers(headers)
                        .delete("/pet/" + ((petId == null) ? "" : petId))
                        .then().extract().response(),
                true);
        return response.as(ApiResponseDTO.class);
    }

    public static void deletesPetWithError(Long petId, String apiKey, Integer statusCode) {
        Map<String, String> headers = new HashMap<>();
        if (apiKey != null) headers.put("api_key", apiKey);

        response = isSuccess(getBaseSpecification()
                        .headers(headers)
                        .delete("/pet/" + ((petId == null) ? "" : petId))
                        .then().extract().response(),
                false);
        Assertions.assertEquals(statusCode, response.getStatusCode());
    }
}
