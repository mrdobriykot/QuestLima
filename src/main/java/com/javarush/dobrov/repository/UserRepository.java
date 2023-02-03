package com.javarush.dobrov.repository;

import com.javarush.dobrov.entity.User;

import java.util.stream.Stream;

public class UserRepository extends BaseRepository<User> {
    @Override
    public Stream<User> find(User pattern) {
        return null;
    }
}
