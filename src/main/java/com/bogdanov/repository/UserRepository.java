package com.bogdanov.repository;

import com.bogdanov.entity.Role;
import com.bogdanov.entity.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class UserRepository extends BaseRepository<User>{

    public UserRepository() {
        create(new User(1L,"Artemiy","123",Role.USER));
        create( new User(2L,"Artemiy","123",Role.USER));
    }


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
