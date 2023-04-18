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

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class ValidatorDataBase {

    //need read cnn data from config
    public static final String DRIVER = "org.postgresql.Driver";
    public static final String URI = "jdbc:postgresql://localhost:2345/game";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String CLASSPATH_DB_CHANGELOG_XML = "classpath:/db/changelog.xml";

    @SneakyThrows
    public void update() {
        Map<String, Object> config = new HashMap<>();
        Scope.child(config, () -> {
            try (Connection connection = DriverManager.getConnection(URI, USER, PASSWORD);) {
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
