package com.javarush.khmelov.config;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.Scope;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

@Component
public class ValidatorDataBase {

    private final ApplicationProperties properties;

    public static final String CLASSPATH_DB_CHANGELOG_XML = "classpath:/db/changelog.xml";

    public ValidatorDataBase(ApplicationProperties properties) {
        this.properties = properties;
        update();
    }

    @SneakyThrows
    public void update() {
        Map<String, Object> config = new HashMap<>();
        Scope.child(config, () -> {
            try (Connection connection = DriverManager.getConnection(
                    properties.getProperty(ApplicationProperties.HIBERNATE_CONNECTION_URL),
                    properties.getProperty(ApplicationProperties.HIBERNATE_CONNECTION_USERNAME),
                    properties.getProperty(ApplicationProperties.HIBERNATE_CONNECTION_PASSWORD)
            )) {
                JdbcConnection jdbcConnection = new JdbcConnection(connection);
                Database database = DatabaseFactory
                        .getInstance()
                        .findCorrectDatabaseImplementation(jdbcConnection);

                Liquibase liquibase = new liquibase.Liquibase(
                        CLASSPATH_DB_CHANGELOG_XML,
                        new ClassLoaderResourceAccessor(), database);
                liquibase.update(new Contexts(), new LabelExpression());
            }
        });
    }
}
