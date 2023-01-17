package com.javarush.quest.marzhiievskyi.repository;

import com.javarush.quest.marzhiievskyi.entity.User;

import java.util.stream.Stream;

public class UserRepository extends BaseRepository<User> {

    public UserRepository() {
        create(User.builder().id(-1L).login("Desmont").password("123").build());
        create(User.builder().id(-1L).login("Amitamaru").password("1111").build());
        create(User.builder().id(-1L).login("Muraved").password("qwerty").build());
    }

    @Override
    public Stream<User> find(User pattern) {
        return map.values().stream()
                .filter(user -> nullOrEquals(pattern.getId(), user.getId()))
                .filter(user -> nullOrEquals(pattern.getLogin(), user.getLogin()))
                .filter(user -> nullOrEquals(pattern.getPassword(), user.getPassword()));
    }
}
