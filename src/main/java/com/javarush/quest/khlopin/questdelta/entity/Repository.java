package com.javarush.quest.khlopin.questdelta.entity;

import java.util.Collection;

public interface Repository {

    void create(User user);
    User get(long id);
    void delete(long id);
    void update(long idOfOlderUser, User user);
    Collection<User> getAll();
}
