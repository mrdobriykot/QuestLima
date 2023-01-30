package com.javarush.quest.ivanilov.repositories;

import com.javarush.quest.ivanilov.entities.users.Roles;
import com.javarush.quest.ivanilov.entities.users.User;

import java.util.ArrayList;
import java.util.Optional;

public class UserRepository extends BaseRepository<User> {

    public UserRepository() {
        create(User.builder().login("admin").password("admin").role(Roles.ADMIN).gamesPlayed(new ArrayList<>()).build());
        create(User.builder().login("testUser").password("safari").role(Roles.USER).gamesPlayed(new ArrayList<>()).build());
        create(User.builder().login("testUser2").password("google").role(Roles.USER).gamesPlayed(new ArrayList<>()).build());
    }

    @Override
    public Optional<User> find(String pattern) {
        return map.values().stream()
                .filter(user -> user.getLogin().equals(pattern))
                .findFirst();
    }
}
