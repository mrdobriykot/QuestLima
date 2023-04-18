package com.javarush.khmelov.repository.hibernate;

import com.javarush.khmelov.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.util.concurrent.atomic.AtomicInteger;

public class SessionCreator implements AutoCloseable {

    private final SessionFactory sessionFactory;
    private final ThreadLocal<AtomicInteger> levelBox = new ThreadLocal<>();
    private final ThreadLocal<Session> sessionBox = new ThreadLocal<>();

    public SessionCreator() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Quest.class);
        configuration.addAnnotatedClass(Question.class);
        configuration.addAnnotatedClass(Answer.class);
        configuration.addAnnotatedClass(Game.class);
        configuration.addAnnotatedClass(UserInfo.class);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        sessionFactory = configuration.buildSessionFactory();
    }

    public Session getSession() {
        return sessionBox.get()==null
                ?sessionFactory.openSession()
                :sessionBox.get();
    }

    public void beginTransactional() {
        if (levelBox.get() == null) {
            levelBox.set(new AtomicInteger(0));
        }
        AtomicInteger level = levelBox.get();
        if (level.getAndIncrement() == 0) {
            Session session = getSession();
            sessionBox.set(session);
            session.beginTransaction();
        }
    }


    public void endTransactional() {
        AtomicInteger level = levelBox.get();
        Session session = sessionBox.get();
        if (level.decrementAndGet() == 0) {
            try {
                session.getTransaction().commit();
            } catch (RuntimeException e) {
                session.getTransaction().rollback();
                throw e;
            }
        } else {
            session.flush();
        }
    }

    @Override
    public void close() {
        sessionFactory.close();
    }
}
