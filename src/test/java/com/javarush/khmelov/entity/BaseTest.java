package com.javarush.khmelov.entity;

import com.javarush.khmelov.config.SessionCreator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {
    public static final long ID = 1L;
    private SessionCreator sessionCreator;
    protected Session session;
    private Transaction tx;

    @BeforeEach
    void setUp() {
        sessionCreator = new SessionCreator();
        session = sessionCreator.getSession();
        tx = session.beginTransaction();

    }

    @AfterEach
    void tearDown() {
        tx.rollback();
        session.close();
        sessionCreator.close();
    }
}
