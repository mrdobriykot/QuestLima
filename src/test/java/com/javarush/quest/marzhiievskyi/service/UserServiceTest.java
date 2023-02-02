package com.javarush.quest.marzhiievskyi.service;


import com.javarush.quest.marzhiievskyi.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private final UserService userService = UserService.USER_SERVICE;


    @Test
    void create() {
        String login = "ami";
        String password = "123";

        User testUser = getUser(login, password);

        userService.create(testUser);

        Optional<User> optionalUser = userService.get(1L);
        User user = null;

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }

        assertEquals(testUser, user);

    }


    @Test
    void update() {
        String login = "qqq";
        String password = "222";

        Optional<User> optionalUser = userService.get(1L);
        User user = null;

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }

        assert user != null;
        user.setLogin(login);
        user.setPassword(password);
        userService.update(user);

        Optional<User> updatedOptional = userService.get(login, password);

        if (updatedOptional.isPresent()) {
            assert true;
        }
    }


    @Test
    void delete() {
        User user = getUser("login", "pass");
        userService.create(user);

        userService.delete(user);

        Optional<User> userOptional = userService.get(2L);

        if (userOptional.isEmpty()) {
            assert true;
        }
    }

    @Test
    void getAll() {
        User user = getUser("login", "pass");
        userService.create(user);
        assertEquals(2, userService.getAll().size());
    }

    @ParameterizedTest
    @CsvSource({"qwerty, 333",
            "long , des"})
    void get(String login, String password) {


        User newUser = getUser(login, password);
        userService.create(newUser);

        Optional<User> userToTest = userService.get(login, password);
        User user = null;

        if (userToTest.isPresent()) {
            user = userToTest.get();
        }

        assert user != null;
        assertEquals(newUser.getLogin(), user.getLogin());
        assertEquals(newUser.getPassword(), user.getPassword());

    }

    @Test
    void getById() {
        User u = getUser("www", "000");
        userService.create(u);


        Optional<User> optionalUser = userService.get(4L);

        User user = null;

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }

        assert user != null;
        assertEquals(u.getLogin(), user.getLogin());
        assertEquals(u.getPassword(), user.getPassword());
    }


    @Test
    void checkUserExist() {

    }


    private User getUser(String login, String password) {
        return User.builder()
                .login(login)
                .password(password)
                .wins(new ArrayList<>())
                .losses(new ArrayList<>())
                .build();
    }
}