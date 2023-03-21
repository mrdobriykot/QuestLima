package com.javarush.khmelov.repository.hibernate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import com.javarush.khmelov.config.Winter;
import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import com.javarush.khmelov.repository.Repository;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRepositoryCRUDTest {

    private final Repository<User> userRepository= Winter.getBean(UserDbRepository.class);

    public static Stream<Arguments> getSamplePatternForSearch() {
        //several users with different nullable fields (need skipped)
        return Stream.of(
                Arguments.of(User.builder().login("Elena").password("123").build(), 1),
                Arguments.of(User.builder().login("Elena").password("badpass").build(), 0),
                Arguments.of(User.builder().login("Elena").build(), 1),

                Arguments.of(User.builder().login("Andrew").build(), 1),
                Arguments.of(User.builder().password("789").build(), 1),
                Arguments.of(User.builder().role(Role.GUEST).build(), 1),

                Arguments.of(User.builder().login("Ivan").password("456").build(), 1),
                Arguments.of(User.builder().login("Ivan").password("456").role(Role.ADMIN).build(), 1),

                Arguments.of(User.builder().build(), 3),
                Arguments.of(User.builder().id(0L).build(), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("getSamplePatternForSearch")
    @DisplayName("Check find by not null fields")
    public void find(User user, int count) {
        long actualCount = userRepository.find(user).count();
        assertEquals(count, actualCount);
    }

    @Test
    @DisplayName("When get all count=3")
    void getAll() {
        long count = userRepository.getAll().size();
        assertEquals(3, count);
    }
    @Test
    void get() {
        User user = userRepository.get(1L);
        assertEquals(user.getLogin(),"Ivan");
    }

    @Test
    @DisplayName("When create+update+delete tempUser then no Exception")
    void createUpdateDelete() {
        User tempUser = User.builder()
                .login("login_test")
                .password("password_test")
                .role(Role.GUEST)
                .build();
        userRepository.create(tempUser);
        System.out.println("CREATE " + tempUser);

        tempUser.setPassword("password_test_update");
        userRepository.update(tempUser);
        System.out.println("UPDATE " + tempUser);

        userRepository.delete(tempUser);
        System.out.println("DELETE " + tempUser);
        assertTrue(tempUser.getId() > 0);
    }
}