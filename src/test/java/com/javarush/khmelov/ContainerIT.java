package com.javarush.khmelov;

import com.javarush.khmelov.config.*;
import com.javarush.khmelov.service.UserService;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import static com.javarush.khmelov.config.ApplicationProperties.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContainerIT {

    private final static JdbcDatabaseContainer<?> CONTAINER;

    public static final String DOCKER_IMAGE_NAME = "postgres:14.6";

    static {
        //create
        CONTAINER = new PostgreSQLContainer<>(DOCKER_IMAGE_NAME);
        CONTAINER.start();
        //set new properties from TestContainers
        ApplicationProperties properties = Context.getBean(ApplicationProperties.class);
        properties.setProperty(HIBERNATE_CONNECTION_URL, CONTAINER.getJdbcUrl());
        properties.setProperty(HIBERNATE_CONNECTION_USERNAME, CONTAINER.getUsername());
        properties.setProperty(HIBERNATE_CONNECTION_PASSWORD, CONTAINER.getPassword());
        //fill db
        ValidatorDataBase validatorDataBase = Context.getBean(ValidatorDataBase.class);
        UserService userService = Context.getBean(UserService.class);
        Configurator configurator = new Configurator(validatorDataBase,userService);
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
        SessionCreator sessionCreator = Context.getBean(SessionCreator.class);
        assertNotNull(sessionCreator);
    }

}
