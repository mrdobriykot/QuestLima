package com.javarush.quest.khlopin.questlima.entity;

import java.util.Collection;
import java.util.Optional;

public interface Repository<T> {

    void create(String login, String password, Role role);

    User get(long id);
    void delete(long id);
    void update(long idOfOlderUser, T entity);
    Collection<User> getAll();

    Optional<User> find(String login);
}
