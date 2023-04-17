package com.otus.helpers;

import com.otus.controllers.PetController;
import com.otus.controllers.UserController;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

public class ClearHelper {

    public static final Map<Long, Integer> createdPetsId = new HashMap<>();
    public static final Map<String, Integer> createdUsersId = new HashMap<>();

    public static void deleteAllPets() {
        createdPetsId.entrySet().stream()
                .forEach(k -> {
                    if (k.getValue() > 0) {
                        while (true) {
                            int statusCode = PetController.deletesPetAsResponse(k.getKey(), null).getStatusCode();
                            System.out.println("status code: " + statusCode);
                            if (statusCode == 404) {
                                createdPetsId.put(k.getKey(), 0);
                                break;
                            }
                            try {
                                sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
    }

    public static void deleteAllUsers() {
        createdUsersId.entrySet().stream()
                .forEach(k -> {
                    if (k.getValue() > 0) {
                        while (true) {
                            int statusCode = UserController.deleteUserAsResponse(k.getKey()).getStatusCode();
                            System.out.println("status code: " + statusCode);
                            if (statusCode == 404) {
                                createdUsersId.put(k.getKey(), 0);
                                break;
                            }
                            try {
                                sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
    }
}
