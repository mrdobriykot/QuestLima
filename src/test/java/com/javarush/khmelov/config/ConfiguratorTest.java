package com.javarush.khmelov.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConfiguratorTest {

    @Test
    void testConfig(){
        Configurator configurator = Context.getBean(Configurator.class);
        Assertions.assertNotNull(configurator);
    }

}