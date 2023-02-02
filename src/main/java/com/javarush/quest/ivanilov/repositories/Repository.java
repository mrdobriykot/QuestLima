package com.javarush.quest.ivanilov.repositories;


import java.util.Collection;
import java.util.Optional;

public interface Repository<T> {
    Collection<T> getAll();
    T get(long id);
    T create(T entity);
    void delete(T entity);
    T update(T entity);
    Optional<T> find(String pattern);
}
