package com.otus.helpers;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.ThreadLocalRandom;

public class GenerateDataHelper {

    public static Integer getNewId() {
        return ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE - 1);
    }

    public static String getRandomNumber(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    public static String getRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }
}
