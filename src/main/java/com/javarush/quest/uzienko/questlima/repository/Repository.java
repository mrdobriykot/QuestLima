package com.javarush.quest.uzienko.questlima.repository;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

public interface Repository<T> {
    Collection<T> getAll();

    Stream<T> find(T pattern);

    Optional<T> get(long id);

    void create(T entity);

    void update(T entity);

    void delete(T entity);
}
