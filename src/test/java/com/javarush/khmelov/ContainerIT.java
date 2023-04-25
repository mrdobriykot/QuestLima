package com.javarush.khmelov;

import com.javarush.khmelov.config.ApplicationProperties;
import com.javarush.khmelov.config.Configurator;
import com.javarush.khmelov.config.SessionCreator;
import com.javarush.khmelov.config.Spring;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContainerIT {

    private final static JdbcDatabaseContainer<?> CONTAINER;

    public static final String HIBERNATE_CONNECTION_URL = "hibernate.connection.url";
    public static final String HIBERNATE_CONNECTION_USERNAME = "hibernate.connection.username";
    public static final String HIBERNATE_CONNECTION_PASSWORD = "hibernate.connection.password";

    static {
        //create
        CONTAINER = new PostgreSQLContainer<>("postgres:14.6");
        CONTAINER.start();
        //set new properties for TestContainers
        ApplicationProperties properties = Spring.getBean(ApplicationProperties.class);
        properties.setProperty(HIBERNATE_CONNECTION_URL, CONTAINER.getJdbcUrl());
        properties.setProperty(HIBERNATE_CONNECTION_USERNAME, CONTAINER.getUsername());
        properties.setProperty(HIBERNATE_CONNECTION_PASSWORD, CONTAINER.getPassword());
        //fill db
        Configurator configurator = Spring.getBean(Configurator.class);
        configurator.fillEmptyRepository();
    }

    public ContainerIT() {
        init();
    }

    public static void init() {
        System.out.println("init started");
    }

    @Test
    void testSessionCreator() {
        SessionCreator sessionCreator = Spring.getBean(SessionCreator.class);
        assertNotNull(sessionCreator);
    }

}
