package com.otus.helpers;

import com.otus.controllers.PetController;
import com.otus.controllers.UserController;

import java.util.HashMap;
import java.util.Map;

public class ClearHelper {

    public static final Map<Long, Integer> CREATED_PETS_ID = new HashMap<>();
    public static final Map<String, Integer> CREATED_USERS_ID = new HashMap<>();

    public static void deleteAllPets() {
        CREATED_PETS_ID.entrySet().stream()
                .forEach(k -> {
                    if (k.getValue() > 0) {
                        while (true) {
                            int statusCode = PetController.deletesPetAsResponse(k.getKey(), null).getStatusCode();
                            System.out.println("status code: " + statusCode);
                            if (statusCode == 404) {
                                CREATED_PETS_ID.put(k.getKey(), 0);
                                break;
                            }
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
    }

    public static void deleteAllUsers() {
        CREATED_USERS_ID.entrySet().stream()
                .forEach(k -> {
                    if (k.getValue() > 0) {
                        while (true) {
                            int statusCode = UserController.deleteUserAsResponse(k.getKey()).getStatusCode();
                            System.out.println("status code: " + statusCode);
                            if (statusCode == 404) {
                                CREATED_USERS_ID.put(k.getKey(), 0);
                                break;
                            }
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
    }
}
