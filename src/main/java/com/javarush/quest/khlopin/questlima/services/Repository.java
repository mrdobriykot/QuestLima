package com.javarush.quest.khlopin.questlima.services;



import java.util.Collection;
import java.util.Optional;

public interface Repository<T> {


    T get(long id);
    void delete(long id);
    void update(long idOfOlderEntity, T entity);
    Collection<T> getAll();

    Optional<T> find(String login);
}
