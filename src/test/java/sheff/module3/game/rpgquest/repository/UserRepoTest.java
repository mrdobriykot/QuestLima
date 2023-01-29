package sheff.module3.game.rpgquest.repository;

import sheff.module3.game.rpgquest.entity.User;
import sheff.module3.game.rpgquest.init.LocationInit;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class UserRepoTest {

    @Test
    void createUser() {
        UserRepo userRepo = new UserRepo();

        String expectedUsername = "userName";
        userRepo.createUser(expectedUsername);

        User actual = userRepo.getUsers().stream()
                .filter(user -> user.getName().equals(expectedUsername))
                .findFirst()
                .get();

        assertEquals(expectedUsername, actual.getName());
    }

    @Test
    void restartUser() {
        User user = new User(1, "STUB", 1, new LocationRepo(new LocationInit()));
        user.getLocationRepo().getLocationById(2).setBlock(false);

        UserRepo userRepo = new UserRepo();
        userRepo.restartUser(user);

        assertTrue(user.getLocationRepo().getLocationById(2).isBlock());
    }

    @Test
    void deleteUser() {
        List<User> users = new ArrayList<>();
        IntStream.rangeClosed(1, 3)
                .forEach(num -> users.add(new User(num, "name" + num, num, Mockito.mock(LocationRepo.class))));

        UserRepo userRepo = new UserRepo();
        userRepo.getUsers().addAll(users);

        User name = users.get(1);
        userRepo.deleteUser(name);

        assertFalse(userRepo.getUsers().contains(name));
    }

    @Test
    void getUserByName() {
        String userName = "userName";
        User expected = new User(1, userName, 1, Mockito.mock(LocationRepo.class));

        UserRepo userRepo = new UserRepo();
        userRepo.getUsers().add(expected);

        User actual = userRepo.getUserByName(userName);

        assertEquals(expected, actual);
    }
}
