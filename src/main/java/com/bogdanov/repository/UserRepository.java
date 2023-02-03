package com.bogdanov.repository;

import com.bogdanov.entity.Role;
import com.bogdanov.entity.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

public class UserRepository implements Repository<User>{

    private final Map<Long, User> map = new HashMap<>();
    public static final AtomicLong id = new AtomicLong(System.currentTimeMillis());



    public UserRepository() {
        map.put(1L, new User(1L,"Artemiy","123",Role.USER));
        map.put(2L, new User(2L,"Artemiy","123",Role.USER));
    }

    @Override
    public Collection<User> getAll() {
        return map.values();
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


    private boolean nullOrEquals(Object patternFields, Object repoFields) {
        return patternFields == null || patternFields.equals(repoFields);
    }

    @Override
    public User get(long id) {
        return map.get(id);
    }

    @Override
    public void create(User entity) {
        entity.setId((id.incrementAndGet()));
        update(entity);


    }

    @Override
    public void update(User entity) {
        Long id1 = entity.getId();

        map.put(id1, entity);
    }

    @Override
    public void delete(User entity) {
        map.remove(entity.getId());
    }
}
