package com.javarush.alimin.repository;

import java.util.Collection;

public interface Repository<T> {
    Collection<T> getAll();

    T get(long id);

    void create(T entity);
    void update(T entity);

}
