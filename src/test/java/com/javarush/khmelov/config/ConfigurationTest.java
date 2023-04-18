package com.javarush.khmelov.config;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfigurationTest {

    private final Configuration configuration = Spring.getBean(Configuration.class);

    @Test
    void checkWebInf() {
        String fullPath = Objects
                .requireNonNull(ConfigurationTest.class.getResource("/"))
                .toString();
        assertTrue(fullPath.contains(configuration.WEB_INF.toString()));
    }
}