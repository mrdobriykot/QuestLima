package com.javarush.quest.khlopin.questlima.entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository implements Repository {

    private final HashMap<Long, User> userMap = new HashMap<>();

    public static final AtomicLong id = new AtomicLong(3);
    public UserRepository() {
        userMap.put(1L,new User(1L,"Mihail","recd123",Role.ADMIN));
        userMap.put(2L, new User(2L,"Emma","saf423",Role.USER));
        userMap.put(3L, new User(3L, "Valik","fdsgh432",Role.GUEST));
    }

    @Override
    public void create(String login, String password, Role role) {
        long key = id.incrementAndGet();
        userMap.put(key,new User(key,login,password,role));
    }

    @Override
    public User get(long id) {
        return userMap.get(id);
    }

    @Override
    public void delete(long id) {
        userMap.remove(id);
    }

    @Override
    public Collection<User> getAll() {
        return userMap.values();
    }

    @Override
    public void update(long idOfOlderUser, User user) {
        delete(idOfOlderUser);
        userMap.put(idOfOlderUser,user);
    }
}
