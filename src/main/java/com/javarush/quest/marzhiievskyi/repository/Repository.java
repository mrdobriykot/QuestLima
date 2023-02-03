package com.javarush.quest.marzhiievskyi.repository;

import java.util.Collection;
import java.util.stream.Stream;

public interface Repository<T> {

    Stream<T> find(T pattern);
    Collection<T> getAll();
    T get(Long id);
    void create(T entity);
    void update(T entity);
    void delete(T entity);

}
