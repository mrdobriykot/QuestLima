package com.javarush.khmelov.config;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplicationPropertiesTest {

    @Test
    void checkWebInf() {
        String fullPath = Objects
                .requireNonNull(ApplicationPropertiesTest.class.getResource("/"))
                .toString();
        assertTrue(fullPath.contains(ApplicationProperties.WEB_INF.toString()));
    }
}