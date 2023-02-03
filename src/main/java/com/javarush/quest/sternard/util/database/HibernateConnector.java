package com.javarush.quest.sternard.util.database;

import com.javarush.quest.sternard.config.Settings;
import com.javarush.quest.sternard.entities.User;
import com.javarush.quest.sternard.exception.HandlerExceptions;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public final class HibernateConnector {
    private static HibernateConnector instance;
    private final SessionFactory sessionFactory;

    private HibernateConnector() {
        try {
            String hibernateConfig = Settings.get().getHibernateConfig();
            sessionFactory = new Configuration()
                    .configure(hibernateConfig)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new HandlerExceptions(e.getMessage());
        }
    }

    public static SessionFactory getSessionFactory() {
        if (instance == null) {
            instance = new HibernateConnector();
        }
        return instance.sessionFactory;
    }
}