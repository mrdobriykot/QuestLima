package sheff.module3.game.rpgquest.repository;

import sheff.module3.game.rpgquest.entity.User;
import sheff.module3.game.rpgquest.init.LocationInit;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    private int userId = 0;
    @Getter
    private final List<User> users = new ArrayList<>();

    public void createUser(String userName) {
        userId++;
        User user = new User(userId, userName, 1, new LocationRepo(new LocationInit()));
        users.add(user);
    }

    public void restartUser(User user) {
        user.setLocationRepo(new LocationRepo(new LocationInit()));
    }

    public void deleteUser(User user) {
        users.remove(user);
    }

    public User getUserByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        return users.stream()
                .filter(user -> user.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
