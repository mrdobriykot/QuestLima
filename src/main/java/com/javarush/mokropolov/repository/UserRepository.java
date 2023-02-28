package com.javarush.mokropolov.repository;

import java.util.stream.Stream;

import com.javarush.mokropolov.entity.User;

public class UserRepository extends BaseRepository<User> {
    @Override
    public Stream<User> find(User pattern) {
        return map.values()
                .stream()
                .filter(u -> nullOrEquals(pattern.getId(), u.getId()))
                .filter(u -> nullOrEquals(pattern.getLogin(), u.getLogin()))
                .filter(u -> nullOrEquals(pattern.getPassword(), u.getPassword()))
                .filter(u -> nullOrEquals(pattern.getRole(), u.getRole()));
    }
}