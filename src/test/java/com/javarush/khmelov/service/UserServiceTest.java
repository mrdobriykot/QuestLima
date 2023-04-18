package com.javarush.khmelov.service;

import com.javarush.khmelov.config.Winter;
import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setup(){
        userService = Winter.getBean(UserService.class);
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
        User user = userService.get(1L).orElseThrow();
        assertEquals("admin",user.getLogin());
    }

    @Test
    @DisplayName("When create+update+delete tempUser then no Exception")
    void createUpdateDelete() {
        User tempUser = User.builder()
                .login("login_test")
                .password("password_test")
                .role(Role.GUEST)
                .build();
        userService.create(tempUser);
        System.out.println("CREATE " + tempUser);

        tempUser.setPassword("password_test_update");
        userService.update(tempUser);
        System.out.println("UPDATE " + tempUser);

        userService.delete(tempUser);
        System.out.println("DELETE " + tempUser);
        assertTrue(tempUser.getId() > 0);
    }
}