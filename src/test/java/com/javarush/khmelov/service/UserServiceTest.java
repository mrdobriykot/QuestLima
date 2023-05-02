package com.javarush.khmelov.service;

import com.javarush.khmelov.ContainerIT;
import com.javarush.khmelov.config.Context;
import com.javarush.khmelov.config.Spring;
import com.javarush.khmelov.dto.UserTo;
import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setup() {
        ContainerIT.init();
        userService = Context.getBean(UserService.class);
    }

    @Test
    @DisplayName("When get all count=3")
    void getAll() {
        long count = userService.getAll().size();
        assertEquals(3, count);
    }

    @Test
    @DisplayName("When get(1L) then login is 'admin'")
    void get() {
        UserTo user = userService.get(1L).orElseThrow();
        assertEquals("admin", user.getLogin());
    }

    @Test
    @DisplayName("When create+update+delete tempUser then no Exception")
    void createUpdateDelete() {
        UserTo user = userService.create("login_test", "password_test").orElseThrow();
        System.out.println("CREATE " + user);

        user = userService.update(user.getId(), user.getLogin(), "new_password", "GUEST").orElseThrow();
        System.out.println("UPDATE " + user);

        userService.delete(user.getId());
        System.out.println("DELETE " + user);
    }
}