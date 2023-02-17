package com.bogdanov.repository;

import com.bogdanov.entity.User;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public interface Repository<T> {
    Stream<T> find(T pattern);
    Collection<T> getAll();
    T get(long id);
    void create(T entity);

    void update(T entity);
    void delete(T entity);
}
