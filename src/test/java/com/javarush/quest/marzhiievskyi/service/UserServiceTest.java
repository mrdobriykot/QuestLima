package com.javarush.quest.marzhiievskyi.service;

import com.javarush.quest.marzhiievskyi.entity.User;
import com.javarush.quest.marzhiievskyi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {


    public final User AMI_TEST_USER = User.builder()
            .id(0L)
            .login("Ami")
            .password("123")
            .games(new ArrayList<>(10))
            .wins(new ArrayList<>(4))
            .losses(new ArrayList<>(6))
            .build();

    private final UserService USER_SERVICE = UserService.USER_SERVICE;

    @Test
    void create() {
        USER_SERVICE.create(AMI_TEST_USER);

        Optional<User> optionalUser = USER_SERVICE.get(1L);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            assertEquals(AMI_TEST_USER, user);
        }

    }

    @Test
    void update() {
        USER_SERVICE.create(AMI_TEST_USER);

        String login = "desmont";
        String password = "111";

        AMI_TEST_USER.setLogin(login);
        AMI_TEST_USER.setPassword(password);

        USER_SERVICE.update(AMI_TEST_USER);

        Optional<User> optionalUser = USER_SERVICE.get(login, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            assertEquals(login, user.getLogin());
            assertEquals(password, user.getPassword());
        }
    }

    @Test
    void delete() {
        USER_SERVICE.create(AMI_TEST_USER);
        Optional<User> optionalUser = USER_SERVICE.get(1L);

        USER_SERVICE.delete(AMI_TEST_USER);
        Optional<User> deletedUser = USER_SERVICE.get(1L);

        assertTrue(deletedUser.isEmpty());
    }

    @Test
    void getAll() {
        USER_SERVICE.create(AMI_TEST_USER);
        USER_SERVICE.create(AMI_TEST_USER);

        Collection<User> all = USER_SERVICE.getAll();
        assertEquals(2, all.size());
    }


    //TODO parametrized test search by id
    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3})
    void get(long id) {
        USER_SERVICE.create(AMI_TEST_USER);
        Optional<User> optionalUser = USER_SERVICE.get(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            assertEquals(id, user.getId());
        }

    }

    @Test
    void getByLoginAndPassword() {
        String login ="Ami";
        String password = "123";

        Optional<User> optionalUser = USER_SERVICE.get(login, password);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            assertEquals(login, user.getLogin());
            assertEquals(password, user.getPassword());
        }
    }

    @Test
    void getWins() {
    }

    @Test
    void getLosses() {
    }

    @Test
    void checkUserExist() {
    }
}