package com.javarush.quest.marzhiievskyi.repository;

import com.javarush.quest.marzhiievskyi.entity.User;

import java.util.stream.Stream;

public class UserRepository extends BaseRepository<User> {

    @Override
    public Stream<User> find(User pattern) {
        return map.values().stream()
                .filter(user -> nullOrEquals(pattern.getId(), user.getId()))
                .filter(user -> nullOrEquals(pattern.getLogin(), user.getLogin()))
                .filter(user -> nullOrEquals(pattern.getPassword(), user.getPassword()));
    }
}
