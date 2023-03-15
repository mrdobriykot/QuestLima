package com.javarush.khmelov.repository.hibernate;

import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UserDbRepositoryTest {

    private UserDbRepository userDbRepository;

    @BeforeEach
    void setup(){
        userDbRepository = new UserDbRepository(new SessionCreator());
    }

    public static Stream<Arguments> getSamplePatternForSearch() {
        //several users with different nullable fields (need skipped)
        return Stream.of(
                Arguments.of(User.builder().login("admin").password("456").build(), 1),
                Arguments.of(User.builder().login("admin").password("badpass").build(), 0),
                Arguments.of(User.builder().login("admin").build(), 1),

                Arguments.of(User.builder().login("guest").build(), 1),
                Arguments.of(User.builder().password("789").build(), 1),
                Arguments.of(User.builder().role(Role.GUEST).build(), 1),

                Arguments.of(User.builder().login("ivan").password("123").build(), 1),
                Arguments.of(User.builder().login("ivan").password("123").role(Role.USER).build(), 1),

                Arguments.of(User.builder().build(), 3),
                Arguments.of(User.builder().id(0L).build(), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("getSamplePatternForSearch")
    @DisplayName("Check find by not null fields")
    public void find(User user, int count) {
        long actualCount = userDbRepository.find(user).count();
        assertEquals(count, actualCount);
    }

    @Test
    @DisplayName("When get all count=3")
    void getAll() {
        long count = userDbRepository.getAll().size();
        assertEquals(3, count);
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