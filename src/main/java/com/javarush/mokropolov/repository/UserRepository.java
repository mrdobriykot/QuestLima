package com.javarush.mokropolov.repository;



import com.javarush.mokropolov.entity.Role;
import com.javarush.mokropolov.entity.User;

import java.util.stream.Stream;

public class UserRepository extends BaseRepository<User> {
    public UserRepository() {
        create(new User(-1L,"admin","admin", Role.ADMIN));
        create(new User(-1L,"user","qwerty", Role.USER));
        create(new User(-1L,"guest","guest", Role.GUEST));
    }

    @Override
    public Stream<User> find(User pattern) {
        return map.values()
                .stream()
                .filter(user -> nullOrEquals(pattern.getId(), user.getId()))
                .filter(user -> nullOrEquals(pattern.getLogin(), user.getLogin()))
                .filter(user -> nullOrEquals(pattern.getPassword(), user.getPassword()))
                .filter(user -> nullOrEquals(pattern.getRole(), user.getRole()));

    }


}

