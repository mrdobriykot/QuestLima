package com.javarush.quest.ivanilov.repositories;

import com.javarush.quest.ivanilov.entities.users.User;

import java.util.Optional;

public class UserRepository extends BaseRepository<User> {

    public UserRepository() {
    }

    public Optional<User> find(String loginPattern) {
        return map.values().stream()
                .filter(user -> user.getLogin().equals(loginPattern))
                .findFirst();
    }
}
