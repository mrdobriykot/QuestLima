package com.javarush.khmelov.config;

import com.javarush.khmelov.ContainerIT;
import com.javarush.khmelov.entity.User;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionCreatorTest {

    @BeforeAll
    static void setUp() {
        ContainerIT.init();
    }

    @Test
    void getSession() {
        SessionCreator sessionCreator = Spring.getBean(SessionCreator.class);
        Session session = sessionCreator.getSession();
        assertNotNull(session);
        User user = session.load(User.class, 1L);
        assertEquals(1L,user.getId());
    }
}