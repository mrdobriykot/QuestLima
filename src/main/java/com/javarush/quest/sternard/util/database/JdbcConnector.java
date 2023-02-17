package com.javarush.quest.sternard.util.database;

import com.javarush.quest.sternard.config.Settings;
import com.javarush.quest.sternard.exception.HandlerExceptions;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public enum JdbcConnector {
    INSTANCE;
    private HikariDataSource ds;

    JdbcConnector() {
        ds = getHikariDataSource();
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private HikariDataSource getHikariDataSource() {
        String hikariConfig = Settings.get().getHikariConfig();
        try (InputStream loader = JdbcConnector.class.getClassLoader().getResourceAsStream(hikariConfig)) {
            Properties props = new Properties();
            props.load(loader);

            HikariConfig config = new HikariConfig(props);
            ds = new HikariDataSource(config);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
        return ds;
    }
}
