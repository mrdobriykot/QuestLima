package com.javarush.khmelov.repository.hibernate;

import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("When create+update+delete tempUser then no Exception")
    void createUpdateDelete() {
        User tempUser = User.builder()
                .login("login_test")
                .password("password_test")
                .role(Role.GUEST)
                .build();
        userDbRepository.create(tempUser);
        System.out.println("CREATE " + tempUser);

        tempUser.setPassword("password_test_update");
        userDbRepository.update(tempUser);
        System.out.println("UPDATE " + tempUser);

        userDbRepository.delete(tempUser);
        System.out.println("DELETE " + tempUser);
        assertTrue(tempUser.getId() > 0);
    }
}