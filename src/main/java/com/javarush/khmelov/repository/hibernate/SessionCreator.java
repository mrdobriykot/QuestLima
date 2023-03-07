package com.javarush.khmelov.repository.hibernate;

import com.javarush.khmelov.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionCreator implements AutoCloseable{

    private final SessionFactory sessionFactory;

    public SessionCreator() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(User.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }

    @Override
    public void close() throws Exception {
        sessionFactory.close();
    }
}
