package com.javarush.khmelov.repository.hibernate;

import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDbRepositoryTest {

    private UserDbRepository userDbRepository;

    @BeforeEach
    void setup(){
        userDbRepository = new UserDbRepository(new SessionCreator());
    }

    @Test
    void get() {
        User user = userDbRepository.get(1L);
        assertEquals("admin",user.getLogin());
    }

    @Test
    void create() {
        User user = User.builder().login("test").password("password").role(Role.GUEST).build();
        userDbRepository.create(user);
        assertTrue(user.getId()>0);
    }
}